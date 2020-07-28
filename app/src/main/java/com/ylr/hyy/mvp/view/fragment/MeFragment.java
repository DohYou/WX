package com.ylr.hyy.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.ylr.hyy.mvp.view.activity.discovers.MomentsFriendMeActivity;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.contract.MeDetailsContract;
import com.ylr.hyy.mvp.model.MeDetailsModel;
import com.ylr.hyy.mvp.presenter.MeDetailsPresenter;
import com.ylr.hyy.mvp.view.activity.discovers.MomentsCustomerMeActivity;
import com.ylr.hyy.mvp.view.activity.login.LoginActivity;
import com.ylr.hyy.mvp.view.activity.me.BeMemberActivity;
import com.ylr.hyy.mvp.view.activity.me.MeCoinsActivity;
import com.ylr.hyy.mvp.view.activity.me.MeCollectionActivity;
import com.ylr.hyy.mvp.view.activity.me.MeDetailsActivity;
import com.ylr.hyy.mvp.view.activity.me.MeOftenProblemsActivity;
import com.ylr.hyy.mvp.view.activity.me.MeSettingActivity;
import com.ylr.hyy.utils.ItemViewGroup;
import com.ylr.hyy.utils.RoundImageView;
import com.ylr.hyy.utils.ToastUtils;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.ylr.hyy.MVPApplication.HttpType;

public class MeFragment extends BaseFragment<MeDetailsContract.View,MeDetailsContract.Presenter> implements MeDetailsContract.View {
    private static final String TAG = "MeFragment";
    @BindView(R.id.fragment_me1)
    ItemViewGroup fragmentMe1;
    @BindView(R.id.fragment_me2)
    ItemViewGroup fragmentMe2;
    @BindView(R.id.fragment_me3)
    ItemViewGroup fragmentMe3;
    @BindView(R.id.fragment_me4)
    ItemViewGroup fragmentMe4;
    @BindView(R.id.fragment_me5)
    ItemViewGroup fragmentMe5;
    @BindView(R.id.fragment_me6)
    ItemViewGroup fragmentMe6;
    @BindView(R.id.fragment_me7)
    ItemViewGroup fragmentMe7;
    @BindView(R.id.fragment_me8)
    ItemViewGroup fragmentMe8;
    @BindView(R.id.fragment_me9)
    ItemViewGroup fragmentMe9;
    @BindView(R.id.rl_fragment_me_header_personal)
    RelativeLayout rLfragmentMe;



    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews(View view) {

    }

    RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
    @Override
    protected void initDatas() {
        showDialog();
        fragmentMe1.setContent(R.drawable.scanblue, "扫一扫", "", false);
        fragmentMe2.setContent(R.drawable.friend_gray, "朋友动态", "", false);
        fragmentMe3.setContent(R.drawable.home_customer, "客户动态", "", false);
        fragmentMe4.setContent(R.drawable.coinsgray, "我的零钱", "", false);
        fragmentMe5.setContent(R.drawable.collectiongray, "我的收藏", "", false);
        fragmentMe6.setContent(R.drawable.returngray, "推广返现", "", false);
        fragmentMe7.setContent(R.drawable.vipgray, "开通会员", "", false);
        fragmentMe8.setContent(R.drawable.helpgray, "帮助", "", false);
        fragmentMe9.setContent(R.drawable.settinggray, "设置", "", false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.upMeInfo(body);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.upMeInfo(body);

    }

    @Override
    protected MeDetailsContract.Presenter initPresenter() {
        mPresenter = new MeDetailsPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @OnClick({R.id.fragment_me1, R.id.fragment_me2, R.id.fragment_me3, R.id.fragment_me4, R.id.fragment_me5, R.id.fragment_me6,
            R.id.fragment_me7, R.id.fragment_me8,R.id.fragment_me9,R.id.rl_fragment_me_header_personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_me1://扫一扫
                Intent intent = new Intent(activity, CaptureActivity.class);
                /*ZxingConfig是配置类
                 *可以设置是否显示底部布局，闪光灯，相册，
                 * 是否播放提示音  震动
                 * 设置扫描框颜色等
                 * 也可以不传这个参数
                 * */
//                ZxingConfig config = new ZxingConfig();
//                config.setPlayBeep(true);//是否播放扫描声音 默认为true
//                config.setShake(true);//是否震动  默认为true
//                config.setDecodeBarCode(true);//是否扫描条形码 默认为true
//                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
//                config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
//                config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
//                config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
//                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, 1);
                break;
            case R.id.fragment_me2://我的朋友圈
                startActivity(new Intent(activity, MomentsFriendMeActivity.class));
                break;
            case R.id.fragment_me3://我的客户圈
                startActivity(new Intent(activity,MomentsCustomerMeActivity.class));
                break;
            case R.id.fragment_me4://我的零钱
                startActivity(new Intent(activity, MeCoinsActivity.class));
                break;
            case R.id.fragment_me5://我的收藏
                startActivity(new Intent(activity, MeCollectionActivity.class));
                break;
            case R.id.fragment_me6://推广返现
                break;
            case R.id.fragment_me7://开通会员
                startActivity(new Intent(activity, BeMemberActivity.class));
                break;
            case R.id.fragment_me8://帮助
                startActivity(new Intent(activity, MeOftenProblemsActivity.class));
                break;
            case R.id.fragment_me9://设置
                startActivity(new Intent(activity, MeSettingActivity.class));
                break;
            case R.id.rl_fragment_me_header_personal://个人资料
                startActivity(new Intent(activity, MeDetailsActivity.class));
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                ToastUtils.showToast(content);
            }
        }
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
        if (code == 401) {
            SPUtils.getInstance().clear();
            Intent intent = new Intent();
            intent.setClass(activity, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            activity.finish();
        }
    }

    @BindView(R.id.riv_me_head)
    RoundImageView rivHead;
    @BindView(R.id.tv_me_name)
    TextView tvName;
    @BindView(R.id.tv_me_id)
    TextView tvId;
    @BindView(R.id.me_tv_huiji)
    TextView tvHuiJi;
    @BindView(R.id.iv_member1)
    ImageView ivMember;
    @BindView(R.id.tv_vip_lv)
    TextView tvLv;
    @Override
    public void upMeInfoSus(MeDetailsModel meDetailsModel) {
        disMissDialog();
        Log.i(TAG, "upMeInfoSus: "+new Gson().toJson(meDetailsModel));
        String data = "{\"data\":{\"balance\":0,\"city\":\"\",\"country\":\"东城区\",\"headimgurl\":\"https://image.renlaibang.com/image_1594635515373\",\"id\":4,\"ispasswrod\":0,\"isrealn\":1,\"nickname\":\"面对疾风吧\",\"onlyaccount\":\"12580\",\"onlyid\":\"97009\",\"paypass\":1,\"phone\":\"18583368210\",\"province\":\"北京市\",\"question\":\"\",\"recommender\":\"97009\",\"recommenderurl\":\"baidu.com\",\"sex\":2,\"signature\":\"啦啦啦啦\",\"status\":1,\"vipgrade\":1,\"vipoverdue\":1597712057000},\"code\":200,\"msg\":\"\",\"success\":true}";
        Type type = new TypeToken<MeDetailsModel>() {}.getType();
        MeDetailsModel meDetailsModel1 = new Gson().fromJson(data, type);
        Log.i(TAG, "upMeInfoSus: "+meDetailsModel.getData().getSignature());

        GlideEngine.loadImage(rivHead,meDetailsModel.getData().getHeadimgurl());
        tvName.setText(meDetailsModel.getData().getNickname());
        tvId.setText("我的ID："+meDetailsModel.getData().getId());
        tvHuiJi.setText("汇集号："+meDetailsModel.getData().getOnlyaccount());

        //会员等级,0普通会员,1vip会员,2超级会员
        switch (meDetailsModel.getData().getVipgrade()) {
            case 0:
                GlideEngine.loadImage(ivMember,R.drawable.changedmember1);
//                Glide.with(this).load(R.drawable.changedmember1).into(ivMember);
                tvLv.setText("普通用户");
                tvLv.setTextColor(Color.parseColor("#81CBFF"));
                break;
            case 1:
//                Glide.with(this).load(R.drawable.changedmember2).into(ivMember);
                GlideEngine.loadImage(ivMember,R.drawable.changedmember3);

                tvLv.setText("会员");
                tvLv.setTextColor(Color.parseColor("#FEC95F"));
                break;
            case 2:
                GlideEngine.loadImage(ivMember,R.drawable.changedmember3);

//                Glide.with(this).load(R.drawable.changedmember3).into(ivMember);
                tvLv.setText("超级");
                tvLv.setTextColor(Color.parseColor("#EEA648"));
                break;
            default:
                break;
        }
    }

    @Override
    public void commitAddressSus(Base base) {

    }
}
