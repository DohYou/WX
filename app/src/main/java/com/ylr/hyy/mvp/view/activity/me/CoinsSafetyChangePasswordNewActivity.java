package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 零钱安全设置 改密码 新密码
 */
public class CoinsSafetyChangePasswordNewActivity extends BaseActivity {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_input_content1)
    ImageView ivInputContent1;
    @BindView(R.id.iv_input_content2)
    ImageView ivInputContent2;
    @BindView(R.id.iv_input_content3)
    ImageView ivInputContent3;
    @BindView(R.id.iv_input_content4)
    ImageView ivInputContent4;
    @BindView(R.id.iv_input_content5)
    ImageView ivInputContent5;
    @BindView(R.id.iv_input_content6)
    ImageView ivInputContent6;
    @BindView(R.id.ll_intercept_event)
    LinearLayout llInterceptEvent;
    @BindView(R.id.tv_change_password_new_next)
    TextView tvChangePasswordNewNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coins_safety_setting_changepassword_new;
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

    }




    @OnClick({R.id.iv_title_return, R.id.tv_change_password_new_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;

            case R.id.tv_change_password_new_next:
                break;
        }
    }
}
