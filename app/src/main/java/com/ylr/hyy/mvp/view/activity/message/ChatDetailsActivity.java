package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.adapter.ChatDetailsAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.view.activity.me.ComplainActivity;
import com.ylr.hyy.utils.RVSpace;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_chatdetails)
    RecyclerView rvChatdetails;
    @BindView(R.id.rl_findsendpicorvideo)
    RelativeLayout rlFindsendpicorvideo;
    @BindView(R.id.rl_findchatcontent)
    RelativeLayout rlFindchatcontent;
    @BindView(R.id.iv_chatdetails_off1)
    ImageView ivChatdetailsOff1;
    @BindView(R.id.iv_chatdetails_on1)
    ImageView ivChatdetailsOn1;
    @BindView(R.id.iv_chatdetails_off2)
    ImageView ivChatdetailsOff2;
    @BindView(R.id.iv_chatdetails_on2)
    ImageView ivChatdetailsOn2;
    @BindView(R.id.iv_chatdetails_off3)
    ImageView ivChatdetailsOff3;
    @BindView(R.id.iv_chatdetails_on3)
    ImageView ivChatdetailsOn3;
    @BindView(R.id.iv_suosecret)
    ImageView ivSuosecret;
    @BindView(R.id.rl_chatdetails_cleanchat)
    RelativeLayout rlChatdetailsCleanchat;
    @BindView(R.id.rl_chatdetails_complain)
    FrameLayout rlChatdetailsComplain;
    private RecyclerView recyclerView;
    private ChatDetailsAdapter adapter;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_chatdetails;
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
        tvTitleName.setText("聊天详情");

        adapter = new ChatDetailsAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerView.addItemDecoration(new RVSpace(2, 2, 2, 2));
        recyclerView.setAdapter(adapter);



    }


    @OnClick({R.id.iv_title_return, R.id.rl_findsendpicorvideo, R.id.rl_findchatcontent, R.id.iv_chatdetails_off1, R.id.iv_chatdetails_on1, R.id.iv_chatdetails_off2, R.id.iv_chatdetails_on2, R.id.iv_chatdetails_off3, R.id.iv_chatdetails_on3, R.id.iv_suosecret, R.id.rl_chatdetails_cleanchat, R.id.rl_chatdetails_complain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.rl_findsendpicorvideo:
                break;
            case R.id.rl_findchatcontent:
                break;
            case R.id.iv_chatdetails_off1:
                ivChatdetailsOn1.setVisibility(View.VISIBLE);
                ivChatdetailsOff1.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_chatdetails_on1:
                ivChatdetailsOff1.setVisibility(View.VISIBLE);
                ivChatdetailsOn1.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_chatdetails_off2:
                ivChatdetailsOn2.setVisibility(View.VISIBLE);
                ivChatdetailsOff2.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_chatdetails_on2:
                ivChatdetailsOff2.setVisibility(View.VISIBLE);
                ivChatdetailsOn2.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_chatdetails_off3:
                ivChatdetailsOn3.setVisibility(View.VISIBLE);
                ivChatdetailsOff3.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_chatdetails_on3:
                ivChatdetailsOff3.setVisibility(View.VISIBLE);
                ivChatdetailsOn3.setVisibility(View.INVISIBLE);
                break;
            case R.id.iv_suosecret:
                break;
            case R.id.rl_chatdetails_cleanchat:
                break;
            case R.id.rl_chatdetails_complain:
                startActivity(new Intent(this, ComplainActivity.class));
                break;
        }
    }

}
