package com.ylr.hyy.mvp.view.activity.login;

import android.content.Intent;
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

/**
 * 冻结客服
 */
public class FreezePeopleActivity extends BaseActivity {
    @BindView(R.id.tv_freeze_immediately_people)
    TextView tvFreezeImmediatelyPeople;
    @BindView(R.id.tv_freeze_code_people)
    TextView tvFreezeCodePeople;
    @BindView(R.id.tv_freeze_password_people)
    TextView tvFreezePasswordPeople;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_freeze_people;
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
        tvTitleName.setText("冻结账号");

    }


    @OnClick({R.id.tv_freeze_immediately_people, R.id.tv_freeze_code_people, R.id.tv_freeze_password_people, R.id.iv_title_return})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_freeze_immediately_people:
                break;
            case R.id.tv_freeze_code_people:
                intent.setClass(this,FreezeCodeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_freeze_password_people:
                intent.setClass(this,FreezePasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_title_return:
                finish();
                break;
        }
    }

}
