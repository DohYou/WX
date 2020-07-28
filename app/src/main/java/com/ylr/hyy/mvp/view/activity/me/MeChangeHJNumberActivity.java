package com.ylr.hyy.mvp.view.activity.me;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeChangeHJNumberContract;
import com.ylr.hyy.mvp.model.MeChangeHJNumberModel;
import com.ylr.hyy.mvp.presenter.MeChangeHJNumberPresenter;
import com.ylr.hyy.mvp.view.dialog.DefaultDialog;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 我的个人资料 更改汇集号
 */
public class MeChangeHJNumberActivity extends BaseActivity<MeChangeHJNumberContract.View,MeChangeHJNumberContract.Presenter> implements MeChangeHJNumberContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_hujiorginalnumber)
    TextView tvHujiorginalnumber;
    @BindView(R.id.iv_changehjnumber_delete)
    ImageView ivChangehjnumberDelete;
    @BindView(R.id.tv_changehjnumber_sure)
    TextView tvChangehjnumberSure;
    @BindView(R.id.et_change_hjnumber)
    EditText editText;

    private DefaultDialog defaultDialog = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medetails_changehjnumber;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeChangeHJNumberContract.Presenter initPresenter() {
      mPresenter = new MeChangeHJNumberPresenter();
      mPresenter.attachView(this);
      return mPresenter;

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("修改汇集号");
        tvHujiorginalnumber.setText("汇集号：" + getIntent().getStringExtra("onlyaccount"));
    }


    @OnClick({R.id.iv_title_return, R.id.iv_changehjnumber_delete, R.id.tv_changehjnumber_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_changehjnumber_delete:
                break;
            case R.id.tv_changehjnumber_sure:
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showToast("请输入新的汇集账号");
                    return;
                }
                new DefaultDialog.Builder().tipsContent("汇集号一年只能修改一次，确认修改？").onSureClickListener(new DefaultDialog.OnSureClickListener() {
                    @Override
                    public void sure() {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("onlyaccount",editText.getText().toString());
                            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
                            showDialog();
                            mPresenter.changeUserHJNumber(body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                }).build().show(getSupportFragmentManager(),"");

                break;
        }
    }

    @Override
    public void changeUserHJNumberSus(MeChangeHJNumberModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());

        finish();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
