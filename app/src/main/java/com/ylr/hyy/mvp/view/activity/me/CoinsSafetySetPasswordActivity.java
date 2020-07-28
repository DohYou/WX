package com.ylr.hyy.mvp.view.activity.me;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.CoinsSafetySetPasswordContract;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;
import com.ylr.hyy.mvp.presenter.CoinsSafetySetPasswordPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 零钱安全设置 设置支付密码
 */
public class CoinsSafetySetPasswordActivity extends BaseActivity<CoinsSafetySetPasswordContract.View,CoinsSafetySetPasswordContract.Presenter> implements CoinsSafetySetPasswordContract.View {
    private static final String TAG = "CoinsSafetySetPasswordA";
    @BindView(R.id.tv_set_password_next_step)
    TextView tvSetPasswordNextStep;
    @BindView(R.id.et_input_content)
    EditText etInputContent;
    @BindViews({R.id.iv_input_content1,R.id.iv_input_content2,R.id.iv_input_content3,R.id.iv_input_content4,R.id.iv_input_content5,R.id.iv_input_content6})
    List<ImageView> inputs;
    @BindView(R.id.tv_coins_safety_type)
    TextView tvType;
    @BindView(R.id.tv_coins_safety_notice)
    TextView tvNotice;
    private InputMethodManager imm = null;


    // 1= 设置支付密码  2= 修改支付密码  3 = 忘记支付密码
    private int type;
    //当前第几次输入
    private int pos = 1;
    //记录每次输入的内容
    private List<String> inputList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_coins_safety_setting_setpassword;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected CoinsSafetySetPasswordContract.Presenter initPresenter() {
        mPresenter = new CoinsSafetySetPasswordPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {
        inputList = new ArrayList<>();
    }

    private boolean isChange;//是否是忘记密码

    @Override
    protected void initDatas() {
        type = getIntent().getIntExtra("type",1);
        isChange = getIntent().getBooleanExtra("isChange",false);
        if (type == 2) {
            tvType.setText("输入原密码");
            tvNotice.setText("请输入原密码");
        }
        etInputContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etInputContent.getText().toString())) {
                    tvSetPasswordNextStep.setBackgroundResource(R.drawable.re_solid_r25_purple);
                    for (int i = 0; i < inputs.size(); i++) {
                        if (etInputContent.getText().toString().length() > i) {
                            inputs.get(i).setBackgroundResource(R.drawable.starsixpassword);
                        }else {
                            inputs.get(i).setBackgroundResource(R.drawable.inputsixpassword);
                        }
                    }
                    if (etInputContent.getText().toString().length() == 6) {
                        switch (type) {
                            case 1:
                                if (pos == 1) {
                                    pos++;
                                    inputList.add(etInputContent.getText().toString());
                                    etInputContent.setText("");
                                    tvType.setText("确认密码");
                                    tvNotice.setText("请重新输入");
                                }
                                if (pos == 2){
                                    if (inputList.size() == 2) {
                                        inputList.remove(1);
                                    }
                                    inputList.add(etInputContent.getText().toString());
                                }
                                break;
                            case 2:
                                if (pos == 1) {
                                    pos++;
                                    inputList.add(etInputContent.getText().toString());
                                    etInputContent.setText("");
                                    tvType.setText("输入新密码");
                                    tvNotice.setText("请输入6位数密码");
                                }else if (pos == 2){
                                    pos++;
                                    inputList.add(etInputContent.getText().toString());
                                    etInputContent.setText("");
                                    tvType.setText("确认密码");
                                    tvNotice.setText("请重新输入");
                                }else {
                                    if (inputList.size() == 3) {
                                        inputList.remove(2);
                                    }
                                    inputList.add(etInputContent.getText().toString());
                                    Log.i(TAG, "afterTextChanged:?????????????? "+inputList.size());
                                }
                                break;
                            case 3:
                                break;
                        }
                    }
                }else {
                    tvSetPasswordNextStep.setBackgroundResource(R.drawable.re_solid_r25_gray);
                    for (int i = 0; i < inputs.size(); i++) {
                        inputs.get(i).setBackgroundResource(R.drawable.inputsixpassword);
                    }
                }
            }
        });
    }


    @OnClick({R.id.tv_set_password_next_step, R.id.iv_title_return,R.id.ll_intercept_event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                 finish();
                Log.i(TAG, "onViewClicked: "+etInputContent.getText().toString());
                break;
            case R.id.tv_set_password_next_step:
//                startActivity(new Intent(this, CoinsSafetySurePasswordActivity.class));
                Log.i(TAG, "onViewClicked: "+inputList.size());
                if (etInputContent.getText().toString().length() != 6) {
                    ToastUtils.showToast("请输入6位数密码");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                switch (type) {
                    case 1:
                        if (inputList.size() != 2) {
                            ToastUtils.showToast("请输入完整6位数密码");
                            return;
                        }
                        if (!inputList.get(0).equals(inputList.get(1))) {
                            ToastUtils.showToast("两次密码不一致，请重新输入");
                            inputList = new ArrayList<>();
                            tvType.setText("密码设置");
                            tvNotice.setText("请输入6位数密码");
                            etInputContent.setText("");
                            pos = 1;
                            return;
                        }
                        try {
                            jsonObject.put("pass",etInputContent.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
                        showDialog();
                        if (isChange) {
                            mPresenter.findPass(body);
                            return;
                        }
                        mPresenter.coinsSafetySetPassword(body);
                        break;
                    case 2:
                        if (inputList.size() != 3) {
                            ToastUtils.showToast("请输入完整6位数密码");
                            return;
                        }
                        if (!inputList.get(1).equals(inputList.get(2))) {
                            ToastUtils.showToast("两次密码不一致，请重新输入新密码");
                            tvType.setText("输入新密码");
                            tvNotice.setText("请输入6位数密码");
                            inputList.remove(1);
                            inputList.remove(1);
                            etInputContent.setText("");
                            pos = 2;
                            return;
                        }
                        try {
                            jsonObject.put("oldpass",inputList.get(0));
                            jsonObject.put("newpass",inputList.get(1));
                            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
                            showDialog();
                            mPresenter.changePass(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        break;
                }
                break;
            case R.id.ll_intercept_event:
                etInputContent.requestFocus();
                if (imm == null) {
                    imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                }
                imm.showSoftInput(etInputContent,0);
                break;
        }
    }

    @Override
    public void coinsSafetySetPasswordSus(CoinsSafetySetPasswordModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
    }

    @Override
    public void changePassSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        finish();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
