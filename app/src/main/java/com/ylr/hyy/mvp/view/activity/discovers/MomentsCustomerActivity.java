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
 * 发现客户圈
 */

public class MomentsCustomerActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_customermoments_header)
    ImageView ivCustomermomentsHeader;
    @BindView(R.id.tv_customerfriendmoments_name)
    TextView tvCustomerfriendmomentsName;
    @BindView(R.id.tv_friendmoments_sign)
    TextView tvFriendmomentsSign;
    @BindView(R.id.iv_customerfriendmoments_sex)
    ImageView ivCustomerfriendmomentsSex;
    @BindView(R.id.iv_customerfriendmoments_memberlevel)
    ImageView ivCustomerfriendmomentsMemberlevel;
    @BindView(R.id.tv_customerfriendmoments_membertext)
    TextView tvCustomerfriendmomentsMembertext;
    @BindView(R.id.rv_customer)
    RecyclerView rvCustomer;
    @BindView(R.id.iv_customerq_sendpyq)
    ImageView ivCustomerqSendpyq;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moments_customer;
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


    @OnClick({R.id.iv_title_return, R.id.iv_customerq_sendpyq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_customerq_sendpyq:
                Intent intent = new Intent();
                intent.putExtra("iscircle",1);
                intent.setClass(this,SendMomentsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
