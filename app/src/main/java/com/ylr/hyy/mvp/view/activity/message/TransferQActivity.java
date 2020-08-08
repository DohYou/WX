package com.ylr.hyy.mvp.view.activity.message;

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

public class TransferQActivity extends BaseActivity {
    @BindView(R.id.iv_transferfounder_back)
    ImageView ivTransferfounderBack;
    @BindView(R.id.tv_transferfounder_achieve)
    TextView tvTransferfounderAchieve;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transferq;
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


    @OnClick({R.id.iv_transferfounder_back, R.id.tv_transferfounder_achieve})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_transferfounder_back:
                finish();
                break;
            case R.id.tv_transferfounder_achieve:
                break;
        }
    }
}
