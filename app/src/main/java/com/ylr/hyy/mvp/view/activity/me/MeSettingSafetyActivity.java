package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的设置 安全
 */
public class MeSettingSafetyActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_setting1)
    FrameLayout meSetting1;
    @BindView(R.id.me_setting2)
    FrameLayout meSetting2;
    @BindView(R.id.me_setting3)
    FrameLayout meSetting3;
    @BindView(R.id.me_setting4)
    RelativeLayout meSetting4;
    @BindView(R.id.me_setting5)
    FrameLayout meSetting5;
    @BindView(R.id.me_setting6)
    RelativeLayout meSetting6;
    @BindView(R.id.tv_settingsafty_phone)
    TextView tvSettingsaftyPhone;
    @BindView(R.id.iv_mesettingsafty_off1)
    ImageView ivMesettingsaftyOff1;
    @BindView(R.id.iv_mesettingsafty_on1)
    ImageView ivMesettingsaftyOn1;
    @BindView(R.id.iv_mesettingsafty_off2)
    ImageView ivMesettingsaftyOff2;
    @BindView(R.id.iv_mesettingsafty_on2)
    ImageView ivMesettingsaftyOn2;

    @Override
    protected int getLayoutId() {
        return R.layout.me_setting_safty;
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
        tvTitleName.setText("安全");
        tvSettingsaftyPhone.setText(getIntent().getStringExtra("phone"));

    }


    @OnClick({R.id.iv_title_return, R.id.me_setting1,R.id.iv_mesettingsafty_off1,R.id.iv_mesettingsafty_on1,R.id.iv_mesettingsafty_off2,R.id.iv_mesettingsafty_on2,R.id.me_setting2, R.id.me_setting4, R.id.me_setting5, R.id.me_setting6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.me_setting1://设置登录密码
                startActivity(new Intent(this, MeSettingSafetySetLoginPasswordActivity.class));
                break;
            case R.id.me_setting2://忘记登录密码
                startActivity(new Intent(this, MeSettingSafetyForgetLoginPasswordActivity.class));
                break;
            case R.id.me_setting4://锁屏密码
                break;
            case R.id.me_setting5://更改锁屏密码
                break;
            case R.id.me_setting6://摇晃锁屏
                break;
            case R.id.iv_mesettingsafty_off1://锁屏密码
                ivMesettingsaftyOff1.setVisibility(View.INVISIBLE);
                ivMesettingsaftyOn1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesettingsafty_on1:
                ivMesettingsaftyOn1.setVisibility(View.INVISIBLE);
                ivMesettingsaftyOff1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesettingsafty_off2://摇晃锁屏
                ivMesettingsaftyOff2.setVisibility(View.INVISIBLE);
                ivMesettingsaftyOn2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_mesettingsafty_on2:
                ivMesettingsaftyOn2.setVisibility(View.INVISIBLE);
                ivMesettingsaftyOff2.setVisibility(View.VISIBLE);
                break;
        }
    }


}
