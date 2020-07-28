package com.ylr.hyy.mvp.view.activity.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.FreezeCodeContract;
import com.ylr.hyy.mvp.presenter.FreezeCodePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 冻结验证码
 */
public class FreezeCodeActivity extends BaseActivity<FreezeCodeContract.View, FreezeCodeContract.Presenter> implements FreezeCodeContract.View {
    @BindView(R.id.tv_freeze_get_code)
    TextView tvLoginGetCodeFreezeCode;
    @BindView(R.id.tv_freeze_immediately_code)
    TextView tvFreezeImmediatelyCode;
    @BindView(R.id.tv_freeze_password_code)
    TextView tvFreezePasswordCode;
    @BindView(R.id.tv_freeze_people_code)
    TextView tvFreezePeopleCode;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_freezecode_phone)
    EditText etFreezecodePhone;
    @BindView(R.id.et_freezecode_code)
    EditText etFreezecodeCode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_freeze_code;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected FreezeCodeContract.Presenter initPresenter() {
        mPresenter = new FreezeCodePresenter();
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

    private JSONObject jsonObject;
    @OnClick({R.id.tv_freeze_get_code, R.id.tv_freeze_immediately_code, R.id.iv_title_return, R.id.tv_freeze_password_code, R.id.tv_freeze_people_code})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_freeze_get_code:
                if (TextUtils.isEmpty(etFreezecodePhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }

                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone", etFreezecodePhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
                    showDialog();
                    mPresenter.freezeCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_freeze_immediately_code:
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",etFreezecodePhone.getText().toString());
                    jsonObject.put("code",etFreezecodeCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.freeze(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_freeze_password_code:
                intent.setClass(this,FreezePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_freeze_people_code:
                intent.setClass(this,FreezePeopleActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_title_return:
                finish();
                break;
        }
    }


    @Override
    public void freezeCodeSus(Base model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());

        downTimer.start();
        isNowTime = false;

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
    /*定时时间*/
    private int time = 59;
    /*是否可以开始获取验证码，默认为true，用这个来控制是否可以点击获取验证码*/
    private boolean isNowTime = true;
    private CountDownTimer downTimer = new CountDownTimer((time * 1000),1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvLoginGetCodeFreezeCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvLoginGetCodeFreezeCode.setText("获取验证码");
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
