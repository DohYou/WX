package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.PublishImageAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.SendMomentContract;
import com.ylr.hyy.mvp.presenter.SendMomentPresenter;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.utils.ZhiHuGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

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


    private PublishImageAdapter adapter;


    private int size = 10;//当前能选择几张图片
    private List<String> list;//当前rv里面有多少数据
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
//        list.add("添加");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
//        list.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1596006948&di=6fbde7578de6a0746370f522cb077b2a&src=http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg");
    }

    @Override
    protected void initDatas() {
        adapter = new PublishImageAdapter(this);
        recyclerView.addItemDecoration(new RVSpace(10));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        adapter.setList(list);

        iscircle = getIntent().getIntExtra("iscircle",0);
        adapter.setOnUpListListener(() ->
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
                        .forResult(1)
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
            }
        }
    }

    @Override
    public void sendMomentSus(Base base) {
        disMissDialog();
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
    private int iscircle;

    private static final String TAG = "SendMomentsActivity";
    @OnClick({R.id.iv_send_return, R.id.tv_send_moment, R.id.iv_send_visibility, R.id.iv_send_open, R.id.rl_send_no_sees, R.id.rl_send_sees})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_send_return:
                finish();
                break;
            case R.id.tv_send_moment:
                String imgs = list.toString().substring(1);
                Log.i(TAG, "onViewClicked: "+list.toString());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("writtenwords",etContent.getText().toString());
                jsonObject.addProperty("videos",videoPath);
                jsonObject.addProperty("imgs",imgs.substring(0,imgs.length()-1));
                Log.i(TAG, "onViewClicked: "+imgs.substring(0,imgs.length()-1));
                jsonObject.addProperty("latitude","");
                jsonObject.addProperty("longitude","");
                jsonObject.addProperty("address","");
                jsonObject.addProperty("issite",tvAddress.getText().toString().endsWith("是否显示位置")?0:1);//是否显示地址 0否 1显示
                jsonObject.addProperty("iscircle",iscircle);
                jsonObject.addProperty("errouserid","");
                jsonObject.addProperty("okouserid","");
                RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                showDialog();
                mPresenter.sendMoment(body);
                break;
            case R.id.iv_send_visibility:
                break;
            case R.id.iv_send_open:
                break;
            case R.id.rl_send_no_sees:
                break;
            case R.id.rl_send_sees:
                break;
        }
    }
}
