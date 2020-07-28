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
 * 解冻客服
 */

public class UnfreezePeopleActivity extends BaseActivity {

    @BindView(R.id.tv_unfreeze_immediately_people)
    TextView tvUnfreezeImmediatelyPeople;
    @BindView(R.id.tv_unfreeze_code_people)
    TextView tvUnfreezeCodePeople;
    @BindView(R.id.tv_unfreeze_password_people)
    TextView tvUnfreezePasswordPeople;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unfreeze_people;
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
        tvTitleName.setText("解冻账号");

    }


    @OnClick({R.id.iv_title_return, R.id.tv_unfreeze_immediately_people, R.id.tv_unfreeze_code_people, R.id.tv_unfreeze_password_people})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_unfreeze_immediately_people:
                break;
            case R.id.tv_unfreeze_code_people:
                intent.setClass(this,UnfreezeCodeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_unfreeze_password_people:
                intent.setClass(this,UnfreezePasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
