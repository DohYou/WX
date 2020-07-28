package com.ylr.hyy.mvp.view.activity.me;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeChangePhoneContract;
import com.ylr.hyy.mvp.model.MeChangePhoneModel;
import com.ylr.hyy.mvp.presenter.MeChangePhonePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 我的个人资料 更改手机号
 */
public class MeChangePhoneActivity extends BaseActivity<MeChangePhoneContract.View, MeChangePhoneContract.Presenter> implements MeChangePhoneContract.View {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_changephone_save)
    TextView tvChangephoneSave;
    @BindView(R.id.tv_changephone_getCode)
    TextView tvChangePhoneGetCode;
    @BindView(R.id.fl_change_phone_getcode)
    FrameLayout flChangePhoneGetcode;
    @BindView(R.id.et_change_originalphone)
    EditText etChangeOrginalPhone;
    @BindView(R.id.et_changephone_code)
    EditText etCode;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_nowphonenum)
    TextView tvNowPhoneNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medetais_changephone;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeChangePhoneContract.Presenter initPresenter() {
        mPresenter = new MeChangePhonePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("更换手机号");
        tvNowPhoneNum.setText("更换手机号后，下次登录可使用新手机号登录。当前手机号：\n\n" + getIntent().getStringExtra("phone"));
    }

    @Override
    public void changeUserPhoneSus(MeChangePhoneModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        finish();
    }

    @Override
    public void getCodeSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        downTimer.start();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }


    private JSONObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.tv_changephone_save, R.id.tv_changephone_getCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_changephone_save:
                if (TextUtils.isEmpty(etChangeOrginalPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etChangeOrginalPhone.getText().toString());
                    jsonObject.put("code",etCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.changeUserPhone(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_changephone_getCode:
                if (!isNowTime) {
                    return;
                }
                if (TextUtils.isEmpty(etChangeOrginalPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号码");
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etChangeOrginalPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.getCode(body);
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
            tvChangePhoneGetCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvChangePhoneGetCode.setText("获取验证码");
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
