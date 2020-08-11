package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.GetQAdminContract;
import com.ylr.hyy.mvp.model.DelQAdminModel;
import com.ylr.hyy.mvp.model.GetQAdminModel;
import com.ylr.hyy.mvp.presenter.GetQAdminPresenter;
import com.ylr.hyy.utils.ToastUtils;

public class GetQAdminActivity extends BaseActivity<GetQAdminContract.View,GetQAdminContract.Presenter> implements GetQAdminContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_getqadmin;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected GetQAdminContract.Presenter initPresenter() {
        mPresenter = new GetQAdminPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void getQAdminSus(GetQAdminModel model) {
        disMissDialog();

        SPUtils.getInstance().put("AdminCount",model.getAdminCount());


    }

    @Override
    public void delQAdminSus(DelQAdminModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
    }

    @Override
    public void showError(int code, String message) {

    }
}
