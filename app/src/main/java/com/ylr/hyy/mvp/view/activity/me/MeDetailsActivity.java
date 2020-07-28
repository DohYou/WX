package com.ylr.hyy.mvp.view.activity.me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeDetailsContract;
import com.ylr.hyy.mvp.model.AddressModel;
import com.ylr.hyy.mvp.model.MeDetailsModel;
import com.ylr.hyy.mvp.presenter.MeDetailsPresenter;
import com.ylr.hyy.utils.RoundImageView;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 我的个人资料
 */
public class MeDetailsActivity extends BaseActivity<MeDetailsContract.View,MeDetailsContract.Presenter> implements MeDetailsContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.riv_me_details_head)
    RoundImageView rivMeDetailsHead;
    @BindView(R.id.me_details_1)
    FrameLayout meDetails1;
    @BindView(R.id.me_details_2)
    FrameLayout meDetails2;
    @BindView(R.id.me_details_3)
    FrameLayout meDetails3;
    @BindView(R.id.me_details_4)
    FrameLayout meDetails4;
    @BindView(R.id.me_details_5)
    FrameLayout meDetails5;
    @BindView(R.id.me_details_6)
    FrameLayout meDetails6;
    @BindView(R.id.me_details_7)
    FrameLayout meDetails7;
    @BindView(R.id.me_details_8)
    FrameLayout meDetails8;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_me_details_name)
    TextView tvName;
    @BindView(R.id.tv_me_details_phone)
    TextView tvPhone;
    @BindView(R.id.tv_me_details_huiji_code)
    TextView tvHuiJiCode;
    @BindView(R.id.tv_me_details_sex)
    TextView tvSex;
    @BindView(R.id.tv_me_details_sign)
    TextView tvSign;
    @BindView(R.id.tv_me_details_address)
    TextView tvAddress;
    private static final String TAG = "MeDetailsActivity";

    //地址相关
    private Type type;

    private ArrayList<AddressModel> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<String> bankNameList = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private String province;
    private String city;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_details;
    }

    @Override
    protected void initWindow() {
    }

    @Override
    protected MeDetailsContract.Presenter initPresenter() {
        mPresenter = new MeDetailsPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("个人资料");
        showDialog();


    }

    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.upMeInfo(body);
    }

    @OnClick({R.id.iv_title_return, R.id.riv_me_details_head, R.id.me_details_1, R.id.me_details_2, R.id.me_details_3, R.id.me_details_4, R.id.me_details_5, R.id.me_details_6, R.id.me_details_7, R.id.me_details_8})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.riv_me_details_head:
                break;
            case R.id.me_details_1://头像
                if (TextUtils.isEmpty(head)) {
                    return;
                }
                intent.putExtra("head",head);
                intent.setClass(this,HeadImageActivity.class);
                startActivity(intent);
                break;
            case R.id.me_details_2://昵称
                startActivity(new Intent(this,MeChangeNameActivity.class));
                break;
            case R.id.me_details_3://电话
                intent.putExtra("phone",tvPhone.getText().toString());
                intent.setClass(this,MeChangePhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.me_details_4://汇集账号
                intent.putExtra("onlyaccount",tvHuiJiCode.getText().toString());
                intent.setClass(this,MeChangeHJNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.me_details_5://我的二维码
                break;
            case R.id.me_details_6://性别
                startActivity(new Intent(this,MeChangeSexActivity.class));
                break;
            case R.id.me_details_7://个性签名
                startActivity(new Intent(this,MeChangeSignatureActivity.class));
                break;
            case R.id.me_details_8://当前地区

                showDialog();
                Message message = new Message();
                message.what = MSG_LOAD_DATA;
                mHandler.sendMessage(message);
                break;
        }
    }

    private String head;
    @Override
    public void upMeInfoSus(MeDetailsModel meDetailsModel) {
        disMissDialog();
        tvName.setText(meDetailsModel.getData().getNickname());
        tvPhone.setText(meDetailsModel.getData().getPhone());
        tvHuiJiCode.setText(meDetailsModel.getData().getOnlyaccount());
        head = meDetailsModel.getData().getHeadimgurl();
//        Glide.with(this).load(head).into(rivMeDetailsHead);
        GlideEngine.loadImage(rivMeDetailsHead,head);
        if (meDetailsModel.getData().getSex() == 1) {
            tvSex.setText("男");

        }else if (meDetailsModel.getData().getSex() == 2){
            tvSex.setText("女");

        }else {
            tvSex.setText("");
        }
        tvSign.setText(meDetailsModel.getData().getSignature());
        tvAddress.setText(meDetailsModel.getData().getProvince()+" "+meDetailsModel.getData().getCity()+ " " + meDetailsModel.getData().getCountry());
    }

    @Override
    public void commitAddressSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        tvAddress.setText(province + " " + city);
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }


    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        type = new TypeToken<ArrayList<AddressModel>>() {}.getType();
        options1Items = new Gson().fromJson(Utils.getJson("allArea.json",this), type);
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        for (int i = 0 ; i < options1Items.size() ; i++){//遍历省份

            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0 ;  c < options1Items.get(i).getChildrenDTO().size() ; c++){//遍历该省份的所有城市
                String CityName = options1Items.get(i).getChildrenDTO().get(c).getName();
                CityList.add(CityName);//添加城市
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    //判断地址数据是否获取成功
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (isLoaded) {
                        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
                        return;
                    }
                    if (thread==null){//如果已创建就不再重新创建子线程了
                        Log.i(TAG,"地址数据开始解析");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    ShowPickerView();
                    break;
                case MSG_LOAD_FAILED:
                    ToastUtils.showToast("地址数据异常，请稍后重试！");
                    break;

            }
        }
    };

    private void ShowPickerView() {// 弹出地址选择器
        disMissDialog();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            province = options1Items.get(options1).getPickerViewText();
            city = options2Items.get(options1).get(options2);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("province",province);
                jsonObject.put("city",city);
                RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                showDialog();
                mPresenter.commitAddress(body);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        })

                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();
        pvOptions.setPicker(options1Items, options2Items);//二级选择器（市区）
        pvOptions.show();
    }
}
