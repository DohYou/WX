package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.TransferFounderContract;
import com.ylr.hyy.mvp.presenter.TransferFounderPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class QAdminActivity extends BaseActivity<TransferFounderContract.View,TransferFounderContract.Presenter> implements TransferFounderContract.View{
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_qadmin_off1)
    ImageView ivQadminOff1;
    @BindView(R.id.iv_qadmin_on1)
    ImageView ivQadminOn1;
    @BindView(R.id.iv_qadmin_off2)
    ImageView ivQadminOff2;
    @BindView(R.id.iv_qadmin_on2)
    ImageView ivQadminOn2;
    @BindView(R.id.iv_qadmin_off3)
    ImageView ivQadminOff3;
    @BindView(R.id.iv_qadmin_on3)
    ImageView ivQadminOn3;
    @BindView(R.id.rl_setadmin)
    RelativeLayout rlSetadmin;
    @BindView(R.id.rl_inactive)
    RelativeLayout rlInactive;
    @BindView(R.id.rl_qoriginatortransfer)
    RelativeLayout rlQoriginatortransfer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qadmin;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected TransferFounderContract.Presenter initPresenter() {
        mPresenter = new TransferFounderPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("群管理");
    }


    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.transferFounder(body);
    }

    private JSONObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.iv_qadmin_off1, R.id.rl_setadmin, R.id.rl_inactive, R.id.rl_qoriginatortransfer,R.id.iv_qadmin_on1, R.id.iv_qadmin_off2, R.id.iv_qadmin_on2, R.id.iv_qadmin_off3, R.id.iv_qadmin_on3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_qadmin_off1://全员禁言
                ivQadminOff1.setVisibility(View.INVISIBLE);
                ivQadminOn1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qadmin_on1:
                ivQadminOn1.setVisibility(View.INVISIBLE);
                ivQadminOff1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qadmin_off2://群成员保护模式
                ivQadminOff2.setVisibility(View.INVISIBLE);
                ivQadminOn2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qadmin_on2:
                ivQadminOn2.setVisibility(View.INVISIBLE);
                ivQadminOff2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qadmin_off3://开启群认证
                ivQadminOff3.setVisibility(View.INVISIBLE);
                ivQadminOn3.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qadmin_on3:
                ivQadminOn3.setVisibility(View.INVISIBLE);
                ivQadminOff3.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_setadmin:
                startActivity(new Intent(this,SetQAdminActivity.class));
                break;
            case R.id.rl_inactive:
                startActivity(new Intent(this,InactiveQMembersActivity.class));
                break;
            case R.id.rl_qoriginatortransfer:

                break;
        }
    }

    @Override
    public void transferFounderSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void openOrCloseVerifySus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void openOrCloseProhibitSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());

    }

    @Override
    public void openOrCloseProtectSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());

    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
