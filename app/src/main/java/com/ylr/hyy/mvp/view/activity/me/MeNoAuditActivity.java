package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.MeNoAuditContract;
import com.ylr.hyy.mvp.model.MeNoAuditGetCodeModel;
import com.ylr.hyy.mvp.model.MeNoAuditModel;
import com.ylr.hyy.mvp.presenter.MeNoAuditPresenter;
import com.ylr.hyy.utils.TextInputHelper;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 *  我的零钱 实名认证 未认证
 */
public class MeNoAuditActivity extends BaseActivity<MeNoAuditContract.View,MeNoAuditContract.Presenter> implements MeNoAuditContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_commit_vertify_immediately)
    TextView tvCommitVertifyImmediately;
    @BindView(R.id.et_id_name)
    EditText etIdName;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.et_id_bankcard)
    EditText etIdBankcard;
    @BindView(R.id.et_id_phone)
    EditText etIdPhone;
    @BindView(R.id.et_id_code)
    EditText etIdCode;
    @BindView(R.id.tv_noaudit_code)
    TextView tvNoAuditCode;




    private TextInputHelper mInputHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_id_noaudit;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeNoAuditContract.Presenter initPresenter() {
        mPresenter = new MeNoAuditPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("实名认证");
        //创建一个辅助类，传入按钮操作View
        mInputHelper = new TextInputHelper(tvCommitVertifyImmediately);
        //可添加一个或者多个EditText，当然也可以添加TextView
        mInputHelper.addViews(etIdBankcard,etIdCard,etIdCode,etIdPhone,etIdName);
    }


    @OnClick({R.id.iv_title_return,  R.id.tv_commit_vertify_immediately})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_noaudit_code://验证码
                JSONObject jsonObject4 = new JSONObject();
                try {
                    jsonObject4.put("phone",etIdPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject4.toString());
                    showDialog();
                    mPresenter.meNoAuditGetCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_commit_vertify_immediately://立即提交认证
                if (TextUtils.isEmpty(etIdName.getText().toString())) {
                    ToastUtils.showToast("请输入姓名");
                    return;
                }

                if (TextUtils.isEmpty(etIdCard.getText().toString())) {
                    ToastUtils.showToast("请输入身份证号");
                    return;
                }

                if (TextUtils.isEmpty(etIdBankcard.getText().toString())) {
                    ToastUtils.showToast("请输入银行卡号");
                    return;
                }

                if (TextUtils.isEmpty(etIdPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(etIdCode.getText().toString())) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
               JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name",etIdName.getText().toString());
                    jsonObject.put("idCard",etIdCard.getText().toString());
                    jsonObject.put("accountNo",etIdBankcard.getText().toString());
                    jsonObject.put("mobile",etIdPhone.getText().toString());
                    jsonObject.put("code",etIdCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.meNoAudit(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        if (mInputHelper != null) {
            mInputHelper.removeViews();
        }
        super.onDestroy();
    }

    @Override
    public void meNoAuditSus(MeNoAuditModel model) {
        disMissDialog();
        SPUtils.getInstance().put("userName",etIdName.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("idcard",etIdCard.getText().toString());
        intent.putExtra("name",etIdName.getText().toString());
        intent.setClass(this,MeAuditSuccessActivity.class);
        startActivity(intent);
        ToastUtils.showToast(model.getMsg());
        finish();
    }


    @Override
    public void meNoAuditGetCodeSus(MeNoAuditGetCodeModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        downTimer.start();
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
            tvNoAuditCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvNoAuditCode.setText("获取验证码");
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
