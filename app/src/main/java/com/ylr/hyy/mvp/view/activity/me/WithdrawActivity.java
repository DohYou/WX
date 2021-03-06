package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的 零钱 提现
 */
public class WithdrawActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.sure_withdraw)
    TextView sureWithdraw;

    @Override
    protected int getLayoutId() {
        return R.layout.me_coins_withdraw;
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
        tvTitleName.setText("提现");

    }



    @OnClick({R.id.iv_title_return, R.id.sure_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.sure_withdraw:
                break;
        }
    }
}
