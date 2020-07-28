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
import com.ylr.hyy.mvp.contract.UnfreezeCodeContract;
import com.ylr.hyy.mvp.presenter.UnfreezeCodePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 解冻验证码
 */
public class UnfreezeCodeActivity extends BaseActivity<UnfreezeCodeContract.View,UnfreezeCodeContract.Presenter> implements UnfreezeCodeContract.View {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_unfreeze_phone)
    EditText etUnfreezePhone;
    @BindView(R.id.et_unfreeze_code)
    EditText etUnfreezeCode;
    @BindView(R.id.tv_unfreeze_get_code)
    TextView tvUnfreezeGetCode;
    @BindView(R.id.tv_unfreeze_immediately_code)
    TextView tvUnfreezeImmediatelyCode;
    @BindView(R.id.tv_unfreeze_password_code)
    TextView tvUnfreezePasswordCode;
    @BindView(R.id.tv_unfreeze_people_code)
    TextView tvUnfreezePeopleCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_unfreeze_code;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected UnfreezeCodeContract.Presenter initPresenter() {
        mPresenter = new UnfreezeCodePresenter();
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



    private JSONObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.tv_unfreeze_get_code, R.id.tv_unfreeze_immediately_code, R.id.tv_unfreeze_password_code, R.id.tv_unfreeze_people_code})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_unfreeze_get_code:
                if (!isNowTime) {
                    return;
                }
                if (TextUtils.isEmpty(etUnfreezePhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etUnfreezePhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.unFreezeCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_unfreeze_immediately_code:
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",etUnfreezePhone.getText().toString());
                    jsonObject.put("code",etUnfreezeCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.unFreeze(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_unfreeze_password_code:
                intent.setClass(this,UnfreezePasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_unfreeze_people_code:
                intent.setClass(this,UnfreezePeopleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void unFreezeCodeSus(Base model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        downTimer.start();
        isNowTime = false;
    }

    @Override
    public void unFreezeSus(Base model) {
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
            tvUnfreezeGetCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvUnfreezeGetCode.setText("获取验证码");
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
