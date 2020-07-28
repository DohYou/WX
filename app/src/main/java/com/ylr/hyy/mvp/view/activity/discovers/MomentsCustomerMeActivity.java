package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的客户圈
 */

public class MomentsCustomerMeActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_customermemoments_header)
    ImageView ivCustomermemomentsHeader;
    @BindView(R.id.tv_customerfriendmemoments_name)
    TextView tvCustomerfriendmemomentsName;
    @BindView(R.id.tv_friendmoments_sign)
    TextView tvFriendmomentsSign;
    @BindView(R.id.iv_customerfriendmemoments_sex)
    ImageView ivCustomerfriendmemomentsSex;
    @BindView(R.id.iv_customermemoments_memberlevel)
    ImageView ivCustomermemomentsMemberlevel;
    @BindView(R.id.tv_customermoments_membertext)
    TextView tvCustomermomentsMembertext;
    @BindView(R.id.rv_mecustomer)
    RecyclerView rvMecustomer;
    @BindView(R.id.iv_customerqme_sendpyq)
    ImageView ivCustomerqmeSendpyq;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moments_mecustomer;
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


    @OnClick({R.id.iv_title_return, R.id.iv_customerqme_sendpyq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_customerqme_sendpyq:
                startActivity(new Intent(this,SendMomentsActivity.class));
                break;
        }
    }
}
