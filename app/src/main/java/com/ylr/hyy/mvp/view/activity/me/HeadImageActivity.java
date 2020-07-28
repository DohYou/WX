package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.HeadImageContract;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.QiNiuModel;
import com.ylr.hyy.mvp.presenter.HeadImagePresenter;
import com.ylr.hyy.mvp.view.dialog.ChooseFreezeDialog;
import com.ylr.hyy.utils.ResizableImageView;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.utils.ZhiHuGlideEngine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class HeadImageActivity extends BaseActivity<HeadImageContract.View,HeadImageContract.Presenter> implements HeadImageContract.View {
    @BindView(R.id.riv_head_image)
    ResizableImageView riv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_image;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected HeadImageContract.Presenter initPresenter() {
        mPresenter = new HeadImagePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    //默认是我的头像，1是好友头像
    private int type;
    private String headPath;
    private ChooseFreezeDialog dialog1 = null;

    @Override
    protected void initDatas() {
        headPath = getIntent().getStringExtra("head");
        type = getIntent().getIntExtra("type", 0);
//        Glide.with(this).load(headPath).into(riv);
        GlideEngine.loadImage(riv,headPath);
    }

    @OnClick({R.id.iv_head_image_return, R.id.iv_show_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head_image_return:
                finish();
                break;
            case R.id.iv_show_dialog:
                if (type == 0) {
                    if (dialog1 == null) {
                        dialog1 = new ChooseFreezeDialog();
                        dialog1.setText("从手机相册选择","保存到手机");
                        dialog1.setOnChooseListener(new ChooseFreezeDialog.OnChooseListener() {
                            @Override
                            public void freeze() {
                                Matisse
                                        .from(HeadImageActivity.this)
                                        //选择图片
                                        .choose(MimeType.ofImage())
                                        //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                                        .showSingleMediaType(true)
                                        //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                                        .capture(true)
                                        .captureStrategy(new CaptureStrategy(true,"PhotoPicker"))
                                        //最大选择数量
                                        .maxSelectable(1)
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
                            }
                        });
                    }
                    dialog1.show(getSupportFragmentManager(),"");
                    return;
                }

                break;
        }
    }


    private String imagePath;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            //图片路径 同样视频地址也是这个 根据requestCode
            List<String> pathList = Matisse.obtainPathResult(data);
            if (pathList.size() != 0) {
                showDialog();
                ToastUtils.showToast("请稍后");
                sendImageQiNiu(pathList.get(0), SPUtils.getInstance().getString("qiNiuToken"));
            }
        }
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }


    public void sendImageQiNiu(String filePath,String qiniuToken) {
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(20)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone2)
                .build();
        UploadManager uploadManager = new UploadManager(config);

        long time = new Date().getTime();
        String fileName = "image_" + time;
        File uploadFile = new File(filePath);
        uploadManager.put(uploadFile, fileName, qiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                Log.i(TAG, "complete: "+info.error);
                if (info.isOK()) {
                    imagePath = "https://image.renlaibang.com/" + key;
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("handimg",imagePath);
                        RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                        mPresenter.changeUserHeader(body);
                    } catch (JSONException e) {
                        disMissDialog();
                        ToastUtils.showToast("上传失败,请稍后重试!");
                        e.printStackTrace();
                    }

                }else {
                    disMissDialog();
                    ToastUtils.showToast(info.error);
                }
            }
        }, null);
    }
    private static final String TAG = "HeadImageActivity";

    @Override
    public void changeUserHeaderSus(MeChangeHeaderModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        finish();
    }
}
