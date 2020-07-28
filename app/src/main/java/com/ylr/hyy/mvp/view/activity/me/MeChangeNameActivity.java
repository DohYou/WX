package com.ylr.hyy.mvp.view.activity.me;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeChangeNameContract;
import com.ylr.hyy.mvp.model.MeChangeNameModel;
import com.ylr.hyy.mvp.presenter.MeChangeNamePresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.utils.Utils.getTime;


/**
 * 我的个人资料 更改名字
 */
public class MeChangeNameActivity extends BaseActivity<MeChangeNameContract.View,MeChangeNameContract.Presenter> implements MeChangeNameContract.View {
    @BindView(R.id.iv_changename_back)
    ImageView ivChangenameBack;
    @BindView(R.id.tv_changename_save)
    TextView tvChangenameSave;
    @BindView(R.id.et_change_name)
    EditText editText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_medetails_changename;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeChangeNameContract.Presenter initPresenter() {
        mPresenter = new MeChangeNamePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }



    @OnClick({R.id.iv_changename_back, R.id.tv_changename_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_changename_back:
                finish();
                break;
            case R.id.tv_changename_save:
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    ToastUtils.showToast("请输入昵称");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("nickname",editText.getText().toString().trim());
                    jsonObject.put("",getTime());
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
                    showDialog();
                    mPresenter.changeUserName(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void changeUserNameSus(MeChangeNameModel model) {
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
