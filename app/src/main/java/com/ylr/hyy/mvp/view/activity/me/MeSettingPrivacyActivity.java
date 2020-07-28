package com.ylr.hyy.mvp.view.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeSettingPrivacyActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_privacy1)
    TextView tvPrivacy1;
    @BindView(R.id.iv_allowfriend)
    ImageView ivAllowfriend;
    @BindView(R.id.iv_allowcustomer)
    ImageView ivAllowcustomer;
    @BindView(R.id.iv_mesprivacy_off1)
    ImageView ivMesprivacyOff1;
    @BindView(R.id.iv_mesprivacy_on1)
    ImageView ivMesprivacyOn1;
    @BindView(R.id.iv_mesprivacy_off2)
    ImageView ivMesprivacyOff2;
    @BindView(R.id.iv_mesprivacy_on2)
    ImageView ivMesprivacyOn2;
    @BindView(R.id.iv_mesprivacy_off3)
    ImageView ivMesprivacyOff3;
    @BindView(R.id.iv_mesprivacy_on3)
    ImageView ivMesprivacyOn3;
    @BindView(R.id.iv_mesprivacy_off4)
    ImageView ivMesprivacyOff4;
    @BindView(R.id.iv_mesprivacy_on4)
    ImageView ivMesprivacyOn4;
    @BindView(R.id.iv_mesprivacy_off5)
    ImageView ivMesprivacyOff5;
    @BindView(R.id.iv_mesprivacy_on5)
    ImageView ivMesprivacyOn5;
    @BindView(R.id.iv_mesprivacy_off6)
    ImageView ivMesprivacyOff6;
    @BindView(R.id.iv_mesprivacy_on6)
    ImageView ivMesprivacyOn6;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mesetting_privacy;
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
        tvTitleName.setText("隐私");
    }



    @OnClick({R.id.iv_title_return, R.id.iv_allowfriend, R.id.iv_allowcustomer,R.id.iv_mesprivacy_off1, R.id.iv_mesprivacy_on1, R.id.iv_mesprivacy_off2, R.id.iv_mesprivacy_on2, R.id.iv_mesprivacy_off3, R.id.iv_mesprivacy_on3, R.id.iv_mesprivacy_off4, R.id.iv_mesprivacy_on4, R.id.iv_mesprivacy_off5, R.id.iv_mesprivacy_on5, R.id.iv_mesprivacy_off6, R.id.iv_mesprivacy_on6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_allowfriend:
                break;
            case R.id.iv_allowcustomer:
                break;
            case R.id.iv_mesprivacy_off1://手机号
                ivMesprivacyOff1.setVisibility(View.INVISIBLE);
                ivMesprivacyOn1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on1:
                ivMesprivacyOn1.setVisibility(View.INVISIBLE);
                ivMesprivacyOff1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_off2://汇集云账号
                ivMesprivacyOff2.setVisibility(View.INVISIBLE);
                ivMesprivacyOn2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on2:
                ivMesprivacyOn2.setVisibility(View.INVISIBLE);
                ivMesprivacyOff2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_off3://群聊
                ivMesprivacyOff3.setVisibility(View.INVISIBLE);
                ivMesprivacyOn3.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on3:
                ivMesprivacyOn3.setVisibility(View.INVISIBLE);
                ivMesprivacyOff3.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_off4://二维码
                ivMesprivacyOff4.setVisibility(View.INVISIBLE);
                ivMesprivacyOn4.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on4:
                ivMesprivacyOn4.setVisibility(View.INVISIBLE);
                ivMesprivacyOff4.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_off5://名片
                ivMesprivacyOff5.setVisibility(View.INVISIBLE);
                ivMesprivacyOn5.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on5:
                ivMesprivacyOn5.setVisibility(View.INVISIBLE);
                ivMesprivacyOff5.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_off6://加我为好友时需要验证
                ivMesprivacyOff6.setVisibility(View.INVISIBLE);
                ivMesprivacyOn6.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesprivacy_on6:
                ivMesprivacyOn6.setVisibility(View.INVISIBLE);
                ivMesprivacyOff6.setVisibility(View.VISIBLE);
                break;
        }
    }
}
