package com.ylr.hyy.mvp.view.activity.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.LoginContract;
import com.ylr.hyy.mvp.model.LoginModel;
import com.ylr.hyy.mvp.presenter.LoginPresenter;
import com.ylr.hyy.mvp.view.activity.MainActivity;
import com.ylr.hyy.mvp.view.dialog.ChooseFreezeDialog;
import com.ylr.hyy.mvp.view.dialog.FreezeDialog;
import com.ylr.hyy.mvp.view.dialog.UnfreezeDialog;
import com.ylr.hyy.utils.InputEditText;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.iet_login_password)
    InputEditText ietLoginPassword;
    @BindView(R.id.fl_login_password)
    FrameLayout flLoginPassword;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.fl_login_code)
    FrameLayout flLoginCode;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_switch_login_way)
    TextView tvSwitchLoginWay;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_treaty)
    TextView tvLoginTreaty;
    @BindView(R.id.check_is_agree_treaty)
    CheckBox checkBox;
    @BindView(R.id.tv_login_get_code)
    TextView tvGetCode;

    private ChooseFreezeDialog chooseFreezeDialog = null;//选择冻结还是解冻
    private FreezeDialog freezeDialog = null;//冻结
    private UnfreezeDialog unfreezeDialog = null;//解冻
    private int type = 0;//0 = 密码登录 1 = 验证码登录

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }


        SpannableStringBuilder builder = new SpannableStringBuilder("登录即代表您已同意《用户协议》与《隐私协议》");
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#237EFE")),9,15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#237EFE")),16,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginTreaty.setText(builder);

        etLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etLoginPhone.getText().toString())) {
                    if (type == 0) {
                        if (!TextUtils.isEmpty(ietLoginPassword.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }else {
                        if (!TextUtils.isEmpty(etLoginCode.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }
                }else {
                    tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                }
            }
        });

        ietLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(ietLoginPassword.getText().toString())) {
                    if (type == 0) {
                        if (!TextUtils.isEmpty(ietLoginPassword.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }else {
                        if (!TextUtils.isEmpty(etLoginCode.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }
                }else {
                    tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                }
            }
        });

        etLoginCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etLoginCode.getText().toString())) {
                    if (type == 0) {
                        if (!TextUtils.isEmpty(ietLoginPassword.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }else {
                        if (!TextUtils.isEmpty(etLoginCode.getText().toString())) {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_purple);
                        }else {
                            tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                        }
                    }
                }else {
                    tvLogin.setBackgroundResource(R.drawable.re_solid_r25_gray);
                }
            }
        });
    }

    @Override
    public void loginSus(LoginModel loginModel) {
        if (loginModel.getData().getStatus() ==0) {
            ToastUtils.showToast("该账户已被冻结/封禁");
            return;
        }
        disMissDialog();
        ToastUtils.showToast("登录成功");
        SPUtils.getInstance().put("qiNiuToken",loginModel.getData().getQiniutoken());
        SPUtils.getInstance().put("token",loginModel.getData().getToken());
        SPUtils.getInstance().put("userSig",loginModel.getData().getUserSig());
        SPUtils.getInstance().put("userId",loginModel.getData().getId());
        Intent intent = new Intent();
        intent.putExtra("isrealn",loginModel.getData().getIsrealn());
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getCodeSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        isNowTime = false;
        downTimer.start();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    private JSONObject jsonObject;
    @OnClick({R.id.tv_login_get_code, R.id.tv_forget_password, R.id.tv_switch_login_way, R.id.tv_login, R.id.tv_register_account, R.id.tv_more, R.id.tv_login_treaty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_get_code://获取验证码
                if (!isNowTime) {
                    return;
                }
                if (TextUtils.isEmpty(etLoginPhone.getText().toString())) {
                    ToastUtils.showToast("请输入登录账号");
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etLoginPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.getCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_forget_password://忘记密码
                if (tvForgetPassword.getText().toString().equals("忘记密码")) {
//                    startActivity(new Intent(this,));
                }else {
                    type = 0;
                    flLoginCode.setVisibility(View.GONE);
                    flLoginPassword.setVisibility(View.VISIBLE);
                    etLoginCode.setText("");
                    tvForgetPassword.setText("忘记密码");
                    tvSwitchLoginWay.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_switch_login_way://切换登录方式
                if (type == 0) {
                    type = 1;
                    flLoginPassword.setVisibility(View.GONE);
                    flLoginCode.setVisibility(View.VISIBLE);
                    ietLoginPassword.setText("");
                    tvSwitchLoginWay.setVisibility(View.GONE);
                    tvForgetPassword.setText("密码登录");
                }else {

                }
                break;
            case R.id.tv_login://登录
                etLoginCode.setText("123456");
                if (TextUtils.isEmpty(etLoginPhone.getText().toString())) {
                    ToastUtils.showToast("请输入登录账号");
                    return;
                }
                if (!checkBox.isChecked()) {
                    ToastUtils.showToast("请同意并勾选用户协议");
                    return;
                }
                //判断输入密码框是否显示
                if (type == 0) {
                    if (TextUtils.isEmpty(ietLoginPassword.getText().toString())) {
                        ToastUtils.showToast("请输入登录密码");
                        return;
                    }
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("username",etLoginPhone.getText().toString());
                        jsonObject.put("password",ietLoginPassword.getText().toString());
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                        showDialog();
                        mPresenter.login(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    if (TextUtils.isEmpty(etLoginCode.getText().toString())) {
                        ToastUtils.showToast("请输入验证码");
                        return;
                    }
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("username",etLoginPhone.getText().toString());
                        jsonObject.put("code",etLoginCode.getText().toString());
                        RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
                        showDialog();
                        mPresenter.login(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tv_register_account:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.tv_more:
                if (chooseFreezeDialog == null) {
                    chooseFreezeDialog = new ChooseFreezeDialog();
                    chooseFreezeDialog.setOnChooseListener(new ChooseFreezeDialog.OnChooseListener() {
                        @Override
                        public void freeze() {//冻结
                           startActivity(new Intent(LoginActivity.this,FreezeCodeActivity.class));
                        }

                        //解冻
                        @Override
                        public void unfreeze() {
                            startActivity(new Intent(LoginActivity.this,UnfreezeCodeActivity.class));
                        }
                    });
                }
                chooseFreezeDialog.show(getSupportFragmentManager(),"");
                break;
            case R.id.tv_login_treaty:
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
            tvGetCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvGetCode.setText("获取验证码");
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
}
