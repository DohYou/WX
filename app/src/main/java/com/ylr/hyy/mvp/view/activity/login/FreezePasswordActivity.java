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
import com.ylr.hyy.mvp.contract.FreezeContract;
import com.ylr.hyy.mvp.presenter.FreezePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 冻结密码
 */
public class FreezePasswordActivity extends BaseActivity<FreezeContract.View, FreezeContract.Presenter> implements FreezeContract.View {


    @BindView(R.id.tv_login_get_code)
    TextView tvLoginGetCode;
    @BindView(R.id.tv_freeze_immediately_password)
    TextView tvFreezeImmediatelyPassword;
    @BindView(R.id.tv_password_code_freeze)
    TextView tvPasswordCodeFreeze;
    @BindView(R.id.tv_password_people_freeze)
    TextView tvPasswordPeopleFreeze;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_freezepass_phone)
    EditText etFreezepassPhone;
    @BindView(R.id.et_freezepass_password)
    EditText etFreezepassPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_freeze_password;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected FreezeContract.Presenter initPresenter() {
        mPresenter = new FreezePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("冻结账号");


    }

    @OnClick({R.id.tv_login_get_code, R.id.iv_title_return, R.id.tv_freeze_immediately_password, R.id.tv_password_code_freeze, R.id.tv_password_people_freeze})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_freeze_immediately_password:
                if (TextUtils.isEmpty(etFreezepassPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etFreezepassPassword.getText().toString())) {
                    ToastUtils.showToast("请输入密码");
                    return;
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",etFreezepassPhone.getText().toString());
                    jsonObject.put("password",etFreezepassPassword.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.freeze(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_password_code_freeze:
                intent.setClass(this,FreezeCodeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_password_people_freeze:
                intent.setClass(this,FreezePeopleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_title_return:
                finish();
                break;

        }

    }

    @Override
    public void freezeSus(Base model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());

    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);

    }

}
