package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.ForgetPayPasswordCodeContract;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;
import com.ylr.hyy.mvp.model.ForgetPayPasswordCodeModel;
import com.ylr.hyy.mvp.presenter.ForgetPayPasswordCodePresenter;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.mvp.view.activity.me.CoinsSafetyForgetNewActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;


/**
 * 零钱安全设置 忘记密码
 */
public class CoinsSafetyForgetActivity extends BaseActivity<ForgetPayPasswordCodeContract.View, ForgetPayPasswordCodeContract.Presenter> implements ForgetPayPasswordCodeContract.View {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_forget_next_step)
    TextView tvForgetNextStep;
    @BindView(R.id.et_forget_phone)
    EditText etForgetPhone;
    @BindView(R.id.et_forget_code)
    EditText etForgetCode;
    @BindView(R.id.tv_forget_getcode)
    TextView tvForgetGetcode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_coins_safety_setting_forget;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected ForgetPayPasswordCodeContract.Presenter initPresenter() {
        mPresenter = new ForgetPayPasswordCodePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }


    @Override
    protected void initDatas() {
        tvTitleName.setText("忘记密码");
    }


    @OnClick({R.id.iv_title_return, R.id.tv_forget_next_step, R.id.tv_forget_getcode})
    public void onViewClicked(View view) {
        JSONObject jsonObject = new JSONObject();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_forget_getcode:
                try {
                    jsonObject.put("phone", etForgetPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
                    showDialog();
                    mPresenter.forgetPayPasswordCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_forget_next_step:
                if (TextUtils.isEmpty(etForgetPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etForgetCode.getText().toString())) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
                try {
                    jsonObject.put("phone",etForgetPhone.getText().toString());
                    jsonObject.put("code",etForgetCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.ForgetCodeIsCorrect(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void forgetPayPasswordCodeSus(ForgetPayPasswordCodeModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        downTimer.start();
    }

    @Override
    public void ForgetCodeIsCorrectSus(ForgetCodeIsCorrectModel model) {
        disMissDialog();
        Intent intent = new Intent();
        intent.putExtra("isChange",true);
        intent.setClass(this,CoinsSafetySetPasswordActivity.class);
        startActivity(intent);
        finish();
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
    private CountDownTimer downTimer = new CountDownTimer((time * 1000), 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvForgetGetcode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvForgetGetcode.setText("获取验证码");
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
