package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的设置 安全 忘记登录密码
 */
public class MeSettingSafetyForgetLoginPasswordActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.fl_me_setting_safety_forget_password1)
    FrameLayout flMeSettingSafetyForgetPassword1;
    @BindView(R.id.rl_me_setting_safety_forgetpassword_getcode)
    RelativeLayout rlMeSettingSafetyForgetpasswordGetcode;
    @BindView(R.id.fl_me_setting_safety_forget_password2)
    FrameLayout flMeSettingSafetyForgetPassword2;
    @BindView(R.id.fl_me_setting_safety_forget_password3)
    FrameLayout flMeSettingSafetyForgetPassword3;
    @BindView(R.id.fl_me_setting_safety_forget_password4)
    FrameLayout flMeSettingSafetyForgetPassword4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_setting_safety_forgetloginpassword;
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
        tvTitleName.setText("忘记密码");

    }



    @OnClick({R.id.iv_title_return, R.id.fl_me_setting_safety_forget_password1, R.id.rl_me_setting_safety_forgetpassword_getcode, R.id.fl_me_setting_safety_forget_password2, R.id.fl_me_setting_safety_forget_password3, R.id.fl_me_setting_safety_forget_password4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.fl_me_setting_safety_forget_password1:
                break;
            case R.id.rl_me_setting_safety_forgetpassword_getcode:
                break;
            case R.id.fl_me_setting_safety_forget_password2:
                break;
            case R.id.fl_me_setting_safety_forget_password3:
                break;
            case R.id.fl_me_setting_safety_forget_password4:
                break;
        }
    }
}
