package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.UpGroupDetailsContract;
import com.ylr.hyy.mvp.model.UpGroupDetailsModel;
import com.ylr.hyy.mvp.presenter.UpGroupDetailsPresenter;
import com.ylr.hyy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class QChangeQNameActivity extends BaseActivity<UpGroupDetailsContract.View, UpGroupDetailsContract.Presenter> implements UpGroupDetailsContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_qchangeqname_myheader)
    ImageView ivQchangeqnameMyheader;
    @BindView(R.id.et_qchange_qname)
    EditText etQchangeQname;
    @BindView(R.id.iv_qchangeqname_delete)
    ImageView ivQchangeqnameDelete;
    @BindView(R.id.tv_qchangeqname_sure)
    TextView tvQchangeqnameSure;

    private String groupId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_qchangeqname;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected UpGroupDetailsContract.Presenter initPresenter() {
        mPresenter = new UpGroupDetailsPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
          groupId = getIntent().getStringExtra("id");
    }

    @Override
    public void upGroupDetailsSus(UpGroupDetailsModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        finish();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    private JsonObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.iv_qchangeqname_delete, R.id.tv_qchangeqname_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_qchangeqname_delete:
                break;
            case R.id.tv_qchangeqname_sure:
                if (TextUtils.isEmpty(etQchangeQname.getText().toString())) {
                    ToastUtils.showToast("请输入你在该群的昵称");
                }
                jsonObject.addProperty("GroupId",groupId);
                jsonObject.addProperty("Type",1);
                jsonObject.addProperty("Msg",etQchangeQname.getText().toString());
                RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                showDialog();
                mPresenter.upGroupDetails(body);
                break;
        }
    }
}
