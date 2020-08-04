package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.MomentsAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.MomentsContract;
import com.ylr.hyy.mvp.model.MeDetailsModel;
import com.ylr.hyy.mvp.model.MomentsModel;
import com.ylr.hyy.mvp.presenter.MomentsPresenter;
import com.ylr.hyy.mvp.view.dialog.InputDialog;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.RoundImageView;
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
    RoundImageView ivCustomermomentsHeader;
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
    private MomentsModel.DataBean dataBean;
    private InputDialog inputDialog = null;

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
        RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
        mPresenter.upMeInfo(body);

        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            isRefresh = true;
            up();
        });

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page ++;
            isRefresh = false;
            up();
        });

        adapter.setOnItemClickListener(new MomentsAdapter.OnItemClickListener() {

            @Override
            public void like(int id, int type, MomentsModel.DataBean dataBean) {
                if (meDetailsModelData == null) {
                    showDialog();
                    ToastUtils.showToast("获取个人信息失败，请稍后重试!");
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
                    mPresenter.upMeInfo(body);
                    return;
                }
                showDialog();
                if (type == 1) {
                    for (int i = 0; i < dataBean.getFabulous().size(); i++) {
                        if (dataBean.getFabulous().get(i).getUserid() == meDetailsModelData.getId()) {
                            dataBean.setLikeuself(0);
                            dataBean.getFabulous().remove(i);
                        }
                    }
                }else {
                    dataBean.setLikeuself(1);
                    MomentsModel.DataBean.FabulousBean fabulousBean = new MomentsModel.DataBean.FabulousBean();
                    fabulousBean.setUserid(meDetailsModelData.getId());
                    fabulousBean.setUsernickname(meDetailsModelData.getNickname());
                    dataBean.getFabulous().add(fabulousBean);
                }
                MomentsActivity.this.dataBean = dataBean;

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("fabulousid",id);
                jsonObject.addProperty("type",type == 1?2:1);
                RequestBody requestBody = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                mPresenter.fabulous(requestBody);
            }

            @Override
            public void reply(int id, String name, MomentsModel.DataBean dataBean) {
                if (meDetailsModelData == null) {
                    showDialog();
                    ToastUtils.showToast("获取个人信息失败，请稍后重试!");
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
                    mPresenter.upMeInfo(body);
                    return;
                }
                MomentsActivity.this.dataBean = dataBean;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("friendid",id);
                if (inputDialog == null) {
                    inputDialog = new InputDialog();
                    inputDialog.setOnCommentListener(content -> {
                        showDialog();
                        MomentsModel.DataBean.EveaBean eveaBean = new MomentsModel.DataBean.EveaBean();
                        eveaBean.setContent(content);
                        eveaBean.setUsernickname(name);
                        MomentsActivity.this.dataBean.getEvea().add(eveaBean);
                        jsonObject.addProperty("usernickname",name);
                        jsonObject.addProperty("content",content);
                        inputDialog.clearText();
                        RequestBody body1 = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                        mPresenter.reply(body1);
                    });
                }
                inputDialog.setDialogHint("回复 " + name + " ：");
                inputDialog.show(getSupportFragmentManager(),"");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        up();
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
    public void defaultBack(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        adapter.upData(this.dataBean);
    }

    private MeDetailsModel.DataBean meDetailsModelData = null;
    @Override
    public void upMeInfoSus(MeDetailsModel meDetailsModel) {
        meDetailsModelData = meDetailsModel.getData();
        Glide.with(this).load(meDetailsModel.getData().getHeadimgurl()).into(ivCustomermomentsHeader);
        tvCustomerfriendmomentsName.setText(meDetailsModel.getData().getNickname());
        tvFriendmomentsSign.setText(meDetailsModel.getData().getSignature());
        Glide.with(this).load(meDetailsModel.getData().getSex() == 1? R.drawable.male:R.drawable.female).into(ivCustomerfriendmomentsSex);
        //会员等级,0普通会员,1vip会员,2超级会员
        switch (meDetailsModel.getData().getVipgrade()) {
            case 0:
                GlideEngine.loadImage(ivCustomerfriendmomentsMemberlevel,R.drawable.changedmember1);
                tvCustomerfriendmomentsMembertext.setText("普通用户");
                tvCustomerfriendmomentsMembertext.setTextColor(Color.parseColor("#81CBFF"));
                break;
            case 1:
                GlideEngine.loadImage(ivCustomerfriendmomentsMemberlevel,R.drawable.changedmember3);
                tvCustomerfriendmomentsMembertext.setText("会员");
                tvCustomerfriendmomentsMembertext.setTextColor(Color.parseColor("#FEC95F"));
                break;
            case 2:
                GlideEngine.loadImage(ivCustomerfriendmomentsMemberlevel,R.drawable.changedmember3);
                tvCustomerfriendmomentsMembertext.setText("超级");
                tvCustomerfriendmomentsMembertext.setTextColor(Color.parseColor("#EEA648"));
                break;
            default:
                break;
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
