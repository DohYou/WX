package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.SetQAdminContract;
import com.ylr.hyy.mvp.presenter.SetQAdminPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class AddQAdminActivity extends BaseActivity<SetQAdminContract.View,SetQAdminContract.Presenter> implements SetQAdminContract.View {
    @BindView(R.id.iv_addqadmin_back)
    ImageView ivAddqadminBack;
    @BindView(R.id.tv_addqadmin_sure)
    TextView tvAddqadminSure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addqadmin;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected SetQAdminContract.Presenter initPresenter() {
        mPresenter = new SetQAdminPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setQAdmin(body);
    }

    private JSONObject jsonObject;
    @OnClick({R.id.iv_addqadmin_back, R.id.tv_addqadmin_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_addqadmin_back:
                finish();
                break;
            case R.id.tv_addqadmin_sure:
                break;
        }
    }

    @Override
    public void setQAdminSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
