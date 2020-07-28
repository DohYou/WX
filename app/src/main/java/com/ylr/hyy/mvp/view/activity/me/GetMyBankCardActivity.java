package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.BankCardAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.GetMyBankCardContract;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.presenter.GetMyBankCardPresenter;
import com.ylr.hyy.mvp.view.dialog.DefaultDialog;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class GetMyBankCardActivity extends BaseActivity<GetMyBankCardContract.View,GetMyBankCardContract.Presenter> implements GetMyBankCardContract.View{
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_addbankcard)
    RecyclerView rvAddbankcard;
    @BindView(R.id.rl_addbankcard)
    RelativeLayout rlAddbankcard;

    private BankCardAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_getmybankcard;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected GetMyBankCardContract.Presenter initPresenter() {
        mPresenter = new GetMyBankCardPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("我的银行卡");

        showDialog();
        adapter = new BankCardAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvAddbankcard.setLayoutManager(manager);
        rvAddbankcard.setAdapter(adapter);
        manager.setOrientation(RecyclerView.VERTICAL);

        adapter.setOnDeleteClickListener(id -> new DefaultDialog.Builder().onSureClickListener(new DefaultDialog.OnSureClickListener() {
            @Override
            public void sure() {
                showDialog();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id",id);
                RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                mPresenter.deleteBank(body);
            }

            @Override
            public void cancel() {

            }
        }).tipsContent("确认删除此银行卡吗？").build().show(getSupportFragmentManager(),""));
    }

    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getMyBankCard(body);
    }

    @OnClick({R.id.iv_title_return, R.id.rl_addbankcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.rl_addbankcard:
                startActivity(new Intent(this,MeBankAddCardActivity.class));
                break;
        }
    }

    @Override
    public void getMyBankCardSus(GetMyBankCardModel model) {
        disMissDialog();
        adapter.setList(model.getData());
    }

    @Override
    public void deleteBankSus(Base base) {
        mPresenter.getMyBankCard(body);
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
