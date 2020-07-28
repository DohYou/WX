package com.ylr.hyy.mvp.view.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的 零钱 充值
 */
public class TopUpActivity extends BaseActivity {

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.now_money)
    TextView nowMoney;
    @BindView(R.id.iv_wechattopup_ok)
    ImageView ivWechattopupOk;
    @BindView(R.id.iv_zfbtopup_ok)
    ImageView ivZfbtopupOk;
    @BindView(R.id.topup_immediately)
    TextView topupImmediately;
    @BindView(R.id.rl_topup_wechat)
    RelativeLayout rlTopupWechat;
    @BindView(R.id.rl_topup_zfb)
    RelativeLayout rlTopupZfb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_top_up;
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
        tvTitleName.setText("充值");


    }


    @OnClick({R.id.iv_title_return, R.id.topup_immediately,R.id.rl_topup_wechat,R.id.rl_topup_zfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.rl_topup_wechat:
                ivWechattopupOk.setVisibility(View.VISIBLE);
                ivZfbtopupOk.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_topup_zfb:
                ivZfbtopupOk.setVisibility(View.VISIBLE);
                ivWechattopupOk.setVisibility(View.INVISIBLE);
                break;
            case R.id.topup_immediately:
                break;
        }
    }

}
