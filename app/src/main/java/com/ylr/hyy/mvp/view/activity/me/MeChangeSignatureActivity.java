package com.ylr.hyy.mvp.view.activity.me;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeChangeSignatureContract;
import com.ylr.hyy.mvp.model.MeChangeSignatureModel;
import com.ylr.hyy.mvp.presenter.MeChangeSignaturePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 修改个性签名
 */

public class MeChangeSignatureActivity extends BaseActivity<MeChangeSignatureContract.View, MeChangeSignatureContract.Presenter> implements MeChangeSignatureContract.View {

    @BindView(R.id.et_change_signature)
    EditText etChangeSignature;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medetails_changepersonalsign;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeChangeSignatureContract.Presenter initPresenter() {
        mPresenter = new MeChangeSignaturePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void meChangeSignatureSus(MeChangeSignatureModel model) {
        disMissDialog();
        ToastUtils.showToast(model.getMsg());
        finish();

    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    private static final String TAG = "MeChangeSignatureActivi";

    @OnClick({R.id.iv_changepersonalsign_back, R.id.tv_changepersonalsign_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_changepersonalsign_back:
                finish();
                break;
            case R.id.tv_changepersonalsign_save:
                Log.i(TAG, "onViewClicked: ");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("signature",etChangeSignature.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
                    showDialog();
                    mPresenter.meChangeSignature(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
