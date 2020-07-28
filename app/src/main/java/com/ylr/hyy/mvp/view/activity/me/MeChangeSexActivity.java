package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MeChangeSexContract;
import com.ylr.hyy.mvp.model.MeChangeSexModel;
import com.ylr.hyy.mvp.presenter.MeChangeSexPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 我的个人资料 更改性别
 */
public class MeChangeSexActivity extends BaseActivity<MeChangeSexContract.View,MeChangeSexContract.Presenter> implements MeChangeSexContract.View{
    @BindView(R.id.iv_changesex_back)
    ImageView ivChangesexBack;
    @BindView(R.id.tv_changesex_save)
    TextView tvChangesexSave;
    @BindView(R.id.tv_personalsign_male)
    TextView tvPersonalsignMale;
    @BindView(R.id.tv_personalsign_female)
    TextView tvPersonalsignFemale;
    @BindView(R.id.rl_changesex_female)
    RelativeLayout rlChangeSexFemale;
    @BindView(R.id.rl_changesex_male)
    RelativeLayout rlChangeSexMale;
    @BindView(R.id.iv_check_female)
    ImageView ivCheckFemale;
    @BindView(R.id.iv_check_male)
    ImageView ivCheckMale;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_medetails_changesex;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeChangeSexContract.Presenter initPresenter() {
        mPresenter = new MeChangeSexPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    private int sex = 0;//值为1时是男性，值为2时是女性，值为0时是未知
    @OnClick({R.id.iv_changesex_back, R.id.tv_changesex_save,R.id.rl_changesex_female,R.id.rl_changesex_male})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_changesex_back:
                finish();
                break;
            case R.id.rl_changesex_female:
                sex = 2;
                ivCheckMale.setVisibility(View.GONE);
                ivCheckFemale.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_changesex_male:
                sex = 1;
                ivCheckFemale.setVisibility(View.GONE);
                ivCheckMale.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_changesex_save:
                if (sex == 0) {
                    ToastUtils.showToast("请选择性别");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("sex",sex);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
                    showDialog();
                    mPresenter.changeUserSex(body);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    @Override
    public void changeUserSexSus(MeChangeSexModel model) {
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
