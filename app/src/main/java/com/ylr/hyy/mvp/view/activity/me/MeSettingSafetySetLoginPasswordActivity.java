package com.ylr.hyy.mvp.view.activity.me;

import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;

/**
 * 我的设置 安全 设置登录密码
 */
public class MeSettingSafetySetLoginPasswordActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_setting_safety_setloginpassword;
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
        tvTitleName.setText("设置密码");
        findViewById(R.id.iv_title_return).setOnClickListener(v -> MeSettingSafetySetLoginPasswordActivity.this.finish());
    }
}
