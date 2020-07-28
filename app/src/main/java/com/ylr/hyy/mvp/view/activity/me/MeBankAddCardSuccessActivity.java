package com.ylr.hyy.mvp.view.activity.me;

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
 * 我的银行卡 添加银行卡成功
 */
public class MeBankAddCardSuccessActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_bank_addcard_successed;
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
        tvTitleName.setText("添加银行卡");

    }



    @OnClick({R.id.iv_title_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                startActivity(new Intent(this,GetMyBankCardActivity.class));
                finish();
                break;
        }
    }
}
