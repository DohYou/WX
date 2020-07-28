package com.ylr.hyy.mvp.view.activity.me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.MeAuditSuccessContract;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.presenter.MeAuditSuccessPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeAuditSuccessActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_audit_successname)
    TextView tvAuditSuccessname;
    @BindView(R.id.tv_audit_successidcard)
    TextView tvAuditSuccessidcard;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_id_vertified;
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

    private static final String TAG = "MeAuditSuccessActivity";
    @Override
    protected void initDatas() {
        tvTitleName.setText("实名认证");
        Log.i(TAG, "initDatas: "+getIntent().getStringExtra("idcard"));
        tvAuditSuccessidcard.setText(getIntent().getStringExtra("idcard"));
        tvAuditSuccessname.setText(getIntent().getStringExtra("name"));
    }


    @OnClick({R.id.iv_title_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
        }
    }


}