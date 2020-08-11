package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.GetQAdminContract;
import com.ylr.hyy.mvp.model.DelQAdminModel;
import com.ylr.hyy.mvp.model.GetQAdminModel;
import com.ylr.hyy.mvp.presenter.GetQAdminPresenter;
import com.ylr.hyy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class SetQAdminActivity extends BaseActivity<GetQAdminContract.View,GetQAdminContract.Presenter> implements GetQAdminContract.View {
    @BindView(R.id.iv_setqadmin_back)
    ImageView ivSetqadminBack;
    @BindView(R.id.tv_setqadmin_instruction)
    TextView tvSetqadminInstruction;
    @BindView(R.id.rv_setqadmin)
    RecyclerView rvSetqadmin;
    @BindView(R.id.tv_setqadmin_add)
    TextView tvSetqadminAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setqadmin;
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

    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getQAdmin(body);

    }

    @OnClick({R.id.iv_setqadmin_back, R.id.tv_setqadmin_instruction, R.id.tv_setqadmin_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setqadmin_back:
                finish();
                break;
            case R.id.tv_setqadmin_instruction:
                startActivity(new Intent(this,AdminPowerActivity.class));
                break;
            case R.id.tv_setqadmin_add:
                startActivity(new Intent(this,AddQAdminActivity.class));
                break;
        }
    }

    @Override
    public void getQAdminSus(GetQAdminModel model) {
        disMissDialog();
    }

    @Override
    public void delQAdminSus(DelQAdminModel model) {
        disMissDialog();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
