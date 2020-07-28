package com.ylr.hyy.mvp.view.activity.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.RegisterContract;
import com.ylr.hyy.mvp.model.LoginModel;
import com.ylr.hyy.mvp.presenter.RegisterPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 注册
 */

public class RegisterActivity extends BaseActivity<RegisterContract.View,RegisterContract.Presenter> implements RegisterContract.View {
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.tv_register_get_code)
    TextView tvRegisterGetCode;
    @BindView(R.id.tv_register_login_immediately)
    TextView tvRegisterLoginImmediately;
    @BindView(R.id.tv_register_login)
    TextView tvRegisterLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        mPresenter = new RegisterPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

        etRegisterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etRegisterPhone.getText().toString())) {
                    tvRegisterLogin.setBackground(getResources().getDrawable(R.drawable.re_solid_r25_purple));
                }else {
                    tvRegisterLogin.setBackground(getResources().getDrawable(R.drawable.re_solid_r25_gray));
                }
            }
        });

    }

    private JSONObject jsonObject;
    @OnClick({R.id.tv_register_get_code, R.id.tv_register_login_immediately, R.id.tv_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_get_code:
                if (!isNowTime) {
                    return;
                }
                if (TextUtils.isEmpty(etRegisterPhone.getText().toString())) {
                    ToastUtils.showToast("请输入登录账号");
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etRegisterPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.getCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_register_login_immediately:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.tv_register_login:
                if (TextUtils.isEmpty(etRegisterPhone.getText().toString())) {
                    ToastUtils.showToast("请输入登录账号");
                    return;
                }
                if (TextUtils.isEmpty(etRegisterCode.getText().toString())){
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etRegisterPhone.getText().toString());
                    jsonObject.put("code",etRegisterCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.registerUser(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    /*定时时间*/
    private int time = 59;
    /*是否可以开始获取验证码，默认为true，用这个来控制是否可以点击获取验证码*/
    private boolean isNowTime = true;
    private CountDownTimer downTimer = new CountDownTimer((time * 1000),1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvRegisterGetCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvRegisterGetCode.setText("获取验证码");
            isNowTime = true;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (this.isFinishing()) {
            downTimer.cancel();
        }
    }

    @Override
    public void registerUserSus(Base registerUserModel) {
        disMissDialog();
        ToastUtils.showToast("注册成功，请登录");
        finish();
    }

    @Override
    public void getCodeSus(Base base) {
        disMissDialog();
        isNowTime = false;
        downTimer.start();
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void loginSus(LoginModel loginModel) {
        disMissDialog();
        ToastUtils.showToast(loginModel.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
