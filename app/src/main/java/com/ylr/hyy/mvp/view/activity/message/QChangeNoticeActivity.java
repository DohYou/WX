package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
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

public class QChangeNoticeActivity extends BaseActivity<UpGroupDetailsContract.View,UpGroupDetailsContract.Presenter> implements UpGroupDetailsContract.View {
    @BindView(R.id.iv_qchangenotice_back)
    ImageView ivQchangenoticeBack;
    @BindView(R.id.tv_sendqnotice)
    TextView tvSendqnotice;
    @BindView(R.id.et_qnotice)
    EditText etQnotice;

    private String groupId;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_qchangenotice;
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
       groupId = getIntent().getStringExtra("groupId");
    }

    private JsonObject jsonObject;
    @OnClick({R.id.iv_qchangenotice_back, R.id.tv_sendqnotice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_qchangenotice_back:
                finish();
                break;
            case R.id.tv_sendqnotice:
                if (!TextUtils.isEmpty(etQnotice.getText().toString())) {
                    ToastUtils.showToast("请输入群公告内容");
                    return;
                }
                jsonObject.addProperty("GroupId",groupId);
                jsonObject.addProperty("Type",3);
                jsonObject.addProperty("Msg",etQnotice.getText().toString());
                RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                showDialog();
                mPresenter.upGroupDetails(body);
                break;
        }
    }

    @Override
    public void upGroupDetailsSus(UpGroupDetailsModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
