package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.MapAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.utils.ScreenShotHelper;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class MapActivity extends BaseActivity implements AMapLocationListener, PoiSearch.OnPoiSearchListener, AMap.OnMapScreenShotListener {
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.rv_map)
    RecyclerView rvMap;


    private static final String TAG = "MapActivity";
    private Double lat;
    private Double lng;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private AMap aMap;
    //定位蓝点
    private MyLocationStyle myLocationStyle;
    //逆地理编码
    private GeocodeSearch geocoderSearch;
    private MapAdapter adapter = null;
    @BindView(R.id.fl_content)
    FrameLayout fl;
    @BindView(R.id.iv_me_address)
    ImageView ivMeAddress;

    private PoiItem poiItem = null;//我的位置   默认选中的位置
    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个功能是去掉地图的logo和放大缩小图标
        mapView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ((ViewGroup) mapView.getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
        mapView.onCreate(savedInstanceState);// 此方法必须重写
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        //初始化定位
        //设置定位回调监听
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果
        mLocationOption.setOnceLocation(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

        aMap = mapView.getMap();
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.i(TAG, "onCameraChange: "+cameraPosition.toString());
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                lat = cameraPosition.target.latitude;
                lng = cameraPosition.target.longitude;
                changePoi();
                Log.i(TAG, "onCameraChangeFinish: "+cameraPosition.toString());
            }
        });

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        geocoderSearch = new GeocodeSearch(this);


        if (adapter == null) {
            adapter = new MapAdapter(this);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(RecyclerView.VERTICAL);
            rvMap.setLayoutManager(manager);
            rvMap.setAdapter(adapter);

            adapter.setOnBackListener(new MapAdapter.OnBackListener() {
                @Override
                public void back(PoiItem poiItem) {
                    try {
                        Log.i(TAG, "back: "+new Gson().toJson(poiItem));
                        jsonObject = new JSONObject();
                        jsonObject.put("lat",poiItem.getLatLonPoint().getLatitude()+"");
                        jsonObject.put("lng",poiItem.getLatLonPoint().getLongitude()+"");
                        jsonObject.put("address", TextUtils.isEmpty(poiItem.getCityName())?poiItem.getTitle():poiItem.getCityName() + " " + poiItem.getTitle());
                        initAMap(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
        }
        super.onDestroy();
    }

    @BindView(R.id.iv_map_return)
    ImageView iv;
    @OnClick({R.id.iv_map_return, R.id.tv_map_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_map_return:
                finish();
                break;
            case R.id.tv_map_send:
                showDialog();
                Message message = new Message();
                handler.sendMessageDelayed(message,1000);
                break;
        }
    }

    //定位回调监听器
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                lng = aMapLocation.getLongitude();
                lat = aMapLocation.getLatitude();
                Log.i(TAG, "onLocationChanged: "+lat+lng);
                if (poiItem == null) {
                    poiItem = new PoiItem("1111",new LatLonPoint(aMapLocation.getLongitude(),aMapLocation.getLatitude()),aMapLocation.getAoiName(),aMapLocation.getStreet()+aMapLocation.getStreetNum());
                }
                changePoi();

            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    private void changePoi(){
        PoiSearch.Query query = new PoiSearch.Query("","");
        PoiSearch search = new PoiSearch(this,query);
        search.setBound(new PoiSearch.SearchBound(new LatLonPoint(lat, lng), 1000));
        search.setOnPoiSearchListener(this);
        search.searchPOIAsyn();
    }

//    搜索附近
    @Override
    public void onPoiSearched(PoiResult result, int i) {
        if (poiItem != null) {
            result.getPois().add(0,poiItem);
        }
        for (int i1 = 0; i1 < result.getPois().size(); i1++) {
            Log.i(TAG, "onPoiSearched: "+result.getPois().get(i1));
            if (TextUtils.isEmpty(result.getPois().get(i1)+"")) {
                result.getPois().remove(i1);
                i1--;
            }
        }
        adapter.setList(result.getPois());
    }

    //移动到指定经纬度
    private void initAMap(Double lat,double lng) {
    }

    private JSONObject jsonObject;

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onMapScreenShot(Bitmap bitmap) {
        Log.i(TAG, "onMapScreenShot: ");
        ScreenShotHelper.saveScreenShot(bitmap, fl, mapView, ivMeAddress);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            disMissDialog();
            if (jsonObject == null) {
                ToastUtils.showToast("暂无地址，请稍后");
                return;
            }
            Intent intent = new Intent();
            try {
                intent.putExtra("lat",jsonObject.get("lat")+"");
                intent.putExtra("lng",jsonObject.get("lng")+"");
                intent.putExtra("address",jsonObject.get("address")+"");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            setResult(RESULT_OK,intent);
            MapActivity.this.finish();
        }
    };
}
