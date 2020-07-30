package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.MomentsAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.MomentsContract;
import com.ylr.hyy.mvp.model.MomentsModel;
import com.ylr.hyy.mvp.presenter.MomentsPresenter;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 发现客户圈
 */

public class MomentsActivity extends BaseActivity<MomentsContract.View,MomentsContract.Presenter> implements MomentsContract.View {
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
    @BindView(R.id.srk_moments)
    SmartRefreshLayout smartRefreshLayout;


    private MomentsAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moments_customer;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MomentsContract.Presenter initPresenter() {
        mPresenter = new MomentsPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        adapter = new MomentsAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvCustomer.addItemDecoration(new RVSpace(0,5,0,2));
        rvCustomer.setLayoutManager(manager);
        rvCustomer.setAdapter(adapter);

        showDialog();
        up();

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                isRefresh = true;
                up();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++;
                isRefresh = false;
                up();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private int page = 1;
    private JsonObject jsonObject = null;
    private boolean isRefresh = true;
    private void up(){
        if (jsonObject == null) {
            jsonObject = new JsonObject();
            jsonObject.addProperty("iscircle",getIntent().getStringExtra("circle"));
            jsonObject.addProperty("size",10);
        }
        jsonObject.addProperty("page",page);
        RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
        mPresenter.upMoments(body);
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

    @Override
    public void upMomentsSus(MomentsModel momentsModel) {
        disMissDialog();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

        if (isRefresh) {
            adapter.setList(momentsModel.getData());
        }else {
            adapter.addList(momentsModel.getData());
        }

    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        ToastUtils.showToast(message);
    }
}
