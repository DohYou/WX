package com.ylr.hyy.mvp.view.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.UnfreezeContract;
import com.ylr.hyy.mvp.presenter.UnfreezePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 解冻密码
 */
public class UnfreezePasswordActivity extends BaseActivity<UnfreezeContract.View, UnfreezeContract.Presenter> implements UnfreezeContract.View {
    @BindView(R.id.tv_login_get_code)
    TextView tvLoginGetCode;
    @BindView(R.id.tv_unfreeze_immediately_password)
    TextView tvUnfreezeImmediatelyPassword;
    @BindView(R.id.tv_password_code_unfreeze)
    TextView tvPasswordCodeUnfreeze;
    @BindView(R.id.tv_password_people_unfreeze)
    TextView tvPasswordPeopleUnfreeze;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_unfreezepass_phone)
    EditText etUnfreezepassPhone;
    @BindView(R.id.et_unfreezepass_password)
    EditText etUnfreezepassPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unfreeze_password;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected UnfreezeContract.Presenter initPresenter() {
        mPresenter = new UnfreezePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("解冻账号");

    }

    @OnClick({R.id.iv_title_return, R.id.tv_login_get_code, R.id.tv_unfreeze_immediately_password, R.id.tv_password_code_unfreeze, R.id.tv_password_people_unfreeze})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_unfreeze_immediately_password:
                if (TextUtils.isEmpty(etUnfreezepassPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etUnfreezepassPassword.getText().toString())) {
                    ToastUtils.showToast("请输入密码");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",etUnfreezepassPhone.getText().toString());
                    jsonObject.put("password",etUnfreezepassPassword.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.unfreeze(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_password_code_unfreeze:
                intent.setClass(this,UnfreezeCodeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_password_people_unfreeze:
                intent.setClass(this,UnfreezePeopleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void unfreezeSus(Base model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

}
