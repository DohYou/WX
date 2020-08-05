package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.PublishImageAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.SendMomentContract;
import com.ylr.hyy.mvp.presenter.SendMomentPresenter;
import com.ylr.hyy.mvp.view.dialog.ChooseFreezeDialog;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.utils.ZhiHuGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;
import static com.ylr.hyy.utils.Utils.getRealPathFromUri;

/**
 * 发朋友圈 发动态
 */
public class SendMomentsActivity extends BaseActivity<SendMomentContract.View, SendMomentContract.Presenter> implements SendMomentContract.View {
    @BindView(R.id.rv_sendmoments)
    RecyclerView recyclerView;
    @BindView(R.id.iv_send_visibility)
    ImageView ivSendVisibility;
    @BindView(R.id.iv_send_open)
    ImageView ivSendOpen;
    @BindView(R.id.tv_whocantsee)
    TextView tvWhocantsee;
    @BindView(R.id.tv_whocansee)
    TextView tvWhocansee;

    private final int VIDEO_CAPTURE = 11;


    private PublishImageAdapter adapter;


    private int size = 10;//当前能选择几张图片
    private List<String> list;//当前rv里面有多少数据
    private List<String> imgList;//上传成功的图片
    private String videoPath = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sendmoments;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected SendMomentContract.Presenter initPresenter() {
        mPresenter = new SendMomentPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {
        list = new ArrayList<>();
        list.add("添加");
        imgList = new ArrayList<>();
    }

    @Override
    protected void initDatas() {
        adapter = new PublishImageAdapter(this);
        recyclerView.addItemDecoration(new RVSpace(2,2,2,2));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        adapter.setList(list);

        iscircle = getIntent().getStringExtra("iscircle");
        adapter.setOnUpListListener(() ->
                {
                    if (list.size() == 1) {
                        ChooseFreezeDialog dialog = new ChooseFreezeDialog();
                        dialog.setText("选择图片","录制视频");
                        dialog.setOnChooseListener(new ChooseFreezeDialog.OnChooseListener() {
                            @Override
                            public void freeze() {//
                                Matisse
                                        .from(SendMomentsActivity.this)
                                        //选择图片
                                        .choose(MimeType.ofImage())
                                        //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                                        .showSingleMediaType(true)
                                        //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                                        .capture(true)
                                        .captureStrategy(new CaptureStrategy(true, "com.thunder.sample.fileprovider"))
                                        //最大选择数量
                                        .maxSelectable(size - list.size())
                                        //选择方向
                                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                        //界面中缩略图的质量
                                        .thumbnailScale(0.8f)
                                        //黑色主题
                                        .theme(R.style.Matisse_Dracula)
                                        //Glide加载方式
                                        .imageEngine(new ZhiHuGlideEngine())
                                        //请求码
                                        .forResult(1);
                            }

                            @Override
                            public void unfreeze() {
                                Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                                //好使
                                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,10485760L);
                                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
                                startActivityForResult(intent,VIDEO_CAPTURE);
                            }
                        });
                        dialog.show(getSupportFragmentManager(),"");
                    }else {
                        Matisse
                                .from(SendMomentsActivity.this)
                                //选择图片
                                .choose(MimeType.ofImage())
                                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                                .showSingleMediaType(true)
                                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true, "com.thunder.sample.fileprovider"))
                                //最大选择数量
                                .maxSelectable(size - list.size())
                                //选择方向
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                //界面中缩略图的质量
                                .thumbnailScale(0.8f)
                                //黑色主题
                                .theme(R.style.Matisse_Dracula)
                                //Glide加载方式
                                .imageEngine(new ZhiHuGlideEngine())
                                //请求码
                                .forResult(1);
                    }
            }
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //图片路径 同样视频地址也是这个 根据requestCode
                List<String> pathList = Matisse.obtainPathResult(data);
                for (int i = 0; i < pathList.size(); i++) {
                    if (list.get(list.size() - 1).equals("添加")) {
                        list.remove(list.size() - 1);
                    }
                    list.add(pathList.get(i));
                    if (list.size() != 9) {
                        list.add("添加");
                    }
                    adapter.setList(list);
                }
            }else if (requestCode == VIDEO_CAPTURE){
                list = new ArrayList<>();
                videoPath = getRealPathFromUri(this,data.getData());
                File file = new File(videoPath);
                Uri uri = Uri.fromFile(file);
                list = new ArrayList<>();
                list.add(uri.getPath());
                adapter.setList(list);
            }else if (requestCode == 2){
                lat = data.getStringExtra("lat");
                lng = data.getStringExtra("lng");
                address = data.getStringExtra("address");
                tvAddress.setText(address);
            }
        }
    }

    private String lat;
    private String lng;
    private String address;

    @Override
    public void sendMomentSus(Base base) {
        ToastUtils.showToast(base.getMsg());
        disMissDialog();
        finish();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    @BindView(R.id.et_send_content)
    EditText etContent;
    @BindView(R.id.tv_send_address)
    TextView tvAddress;

    private int issite = 0;
    private String iscircle;
    private int imgSize = 0;

    private static final String TAG = "SendMomentsActivity";
    @OnClick({R.id.iv_send_return, R.id.tv_send_moment, R.id.iv_send_visibility, R.id.iv_send_open, R.id.rl_send_no_sees, R.id.rl_send_sees
    ,R.id.rl_me_address})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_send_return:
                finish();
                break;
            case R.id.tv_send_moment:
                showDialog();
                if (!list.get(0).equals("添加") && list.size() == 1) {
                    Toast.makeText(this,"上传视频中...请稍后",Toast.LENGTH_LONG).show();
                    sendVideoQiNiu(videoPath,SPUtils.getInstance().getString("qiNiuToken"));
                }else {
                    if (list.size() != 1) {
                        Toast.makeText(this,"上传图片中...请稍后",Toast.LENGTH_LONG).show();
                        sendImgListQiNiu(list.get(imgSize),SPUtils.getInstance().getString("qiNiuToken"));
                    }
                }
                break;
            case R.id.iv_send_visibility:
                break;
            case R.id.rl_me_address:
                intent.setClass(this,MapActivity.class);
                startActivityForResult(intent,2);
                break;
            case R.id.iv_send_open:
                break;
            case R.id.rl_send_no_sees:
                break;
            case R.id.rl_send_sees:
                break;
        }
    }


    //0纯文本   1图片   2 视频
    private void send(int type){
        JsonObject jsonObject = new JsonObject();
        String imgs = "";
        if (type == 1){
            imgs = imgList.toString().substring(1);
        }
        jsonObject.addProperty("imgs",type == 1?imgs.substring(0,imgs.length()-1):"");
        jsonObject.addProperty("writtenwords",etContent.getText().toString());
        jsonObject.addProperty("videos",type == 2?videoPath:"");
        jsonObject.addProperty("latitude",lat);
        jsonObject.addProperty("longitude",lng);
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("issite",tvAddress.getText().toString().endsWith("是否显示位置")?0:1);//是否显示地址 0否 1显示
        jsonObject.addProperty("iscircle",iscircle);
        jsonObject.addProperty("isselfsee","3");
        jsonObject.addProperty("errouserid","");
        jsonObject.addProperty("okouserid","");
        Log.i(TAG, "send: "+jsonObject.toString());
        RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
        mPresenter.sendMoment(body);
    }

    private void sendVideoQiNiu(String filePath,String qiniuToken) {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone2)
                .build();
        UploadManager uploadManager = new UploadManager(config);

        long time = new Date().getTime();
        String fileName = "video_" + time;
        File uploadFile = new File(filePath);
        uploadManager.put(uploadFile, fileName, qiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                Log.i(TAG, "complete: "+info.error);
                if (info.isOK()) {
                    videoPath = "https://image.renlaibang.com/" + key;
                    send(2);
                }else {
                    disMissDialog();
                    ToastUtils.showToast(info.error);
                }
            }
        }, null);
    }

    public void sendImgListQiNiu(String filePath,String qiniuToken) {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone2)
                .build();
        UploadManager uploadManager = new UploadManager(config);

        long time = new Date().getTime();
        String fileName = "img_" + time;
        File uploadFile = new File(filePath);
        uploadManager.put(uploadFile, fileName, qiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                Log.i(TAG, "complete: "+info.error);
                if (info.isOK()) {
                    imgList.add("https://image.renlaibang.com/" + key);
                    Log.i(TAG, "地址: "+imgList.get(imgSize));
                    imgSize ++;
                    if (list.size() != imgSize) {
                        if (!list.get(imgSize).equals("添加")) {
                            sendImgListQiNiu(list.get(imgSize),SPUtils.getInstance().getString("qiNiuToken"));
                        }else {
                            send(1);
                        }
                    }else {
                        send(1);
                    }
                }else {
                    disMissDialog();
                    ToastUtils.showToast(info.error);
                }
            }
        }, null);
    }
}
