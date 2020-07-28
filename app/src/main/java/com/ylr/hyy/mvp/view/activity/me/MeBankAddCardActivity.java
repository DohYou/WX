package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.BankCardAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.AddBankCardContract;
import com.ylr.hyy.mvp.model.AddBankCardModel;
import com.ylr.hyy.mvp.presenter.AddBankCardPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * 我的银行卡 添加银行卡
 */
public class MeBankAddCardActivity extends BaseActivity<AddBankCardContract.View, AddBankCardContract.Presenter> implements AddBankCardContract.View{

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_bankcard_host)
    TextView tvBankcardHost;
    @BindView(R.id.et_addbankcard_card)
    EditText etAddbankcardCard;
    @BindView(R.id.et_addbankcard_phone)
    EditText etAddbankcardPhone;
    @BindView(R.id.et_addbankcard_code)
    EditText etAddbankcardCode;
    @BindView(R.id.tv_me_bank_addcard_getCode)
    TextView tvMeBankAddcardGetCode;
    @BindView(R.id.tv_addbankcard_commit)
    TextView tvAddbankcardCommit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_bank_addcard;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected AddBankCardContract.Presenter initPresenter() {
        mPresenter = new AddBankCardPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("添加银行卡");
        tvBankcardHost.setText(SPUtils.getInstance().getString("userName"));
    }



    @OnClick({R.id.iv_title_return, R.id.tv_me_bank_addcard_getCode, R.id.tv_addbankcard_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_me_bank_addcard_getCode:
                if (!isNowTime) {
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("phone",etAddbankcardPhone.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.bankCode(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_addbankcard_commit:
                if (TextUtils.isEmpty(tvBankcardHost.getText().toString())) {
                    ToastUtils.showToast("请先实名认证");
                }
                if (TextUtils.isEmpty(etAddbankcardCard.getText().toString())) {
                    ToastUtils.showToast("请输入银行卡号");
                    return;
                }
                if (TextUtils.isEmpty(etAddbankcardPhone.getText().toString())) {
                    ToastUtils.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(etAddbankcardCode.getText().toString())) {
                    ToastUtils.showToast("请输入验证码");
                    return;
                }
                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject1.put("accountNo",etAddbankcardCard.getText().toString());
                    jsonObject1.put("mobile",etAddbankcardPhone.getText().toString());
                    jsonObject1.put("code",etAddbankcardCode.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject1.toString());
                    showDialog();
                    mPresenter.addBankCard(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void addBankCardSus(AddBankCardModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        startActivity(new Intent(this,MeBankAddCardSuccessActivity.class));
        finish();
    }

    @Override
    public void bankCodeSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
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
            tvMeBankAddcardGetCode.setText(time + " S");
            time--;
        }

        @Override
        public void onFinish() {
            tvMeBankAddcardGetCode.setText("获取验证码");
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