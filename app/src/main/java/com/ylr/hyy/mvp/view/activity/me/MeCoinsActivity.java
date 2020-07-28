package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.MeAuditSuccessContract;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.presenter.MeAuditSuccessPresenter;
import com.ylr.hyy.utils.ItemViewGroup;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 我的零钱
 */
public class MeCoinsActivity extends BaseActivity<MeAuditSuccessContract.View,MeAuditSuccessContract.Presenter> implements MeAuditSuccessContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_account_balance)
    TextView tvAccountBalance;
    @BindView(R.id.fragment_coins_me1)
    ItemViewGroup fragmentCoinsMe1;
    @BindView(R.id.fragment_coins_me2)
    ItemViewGroup fragmentCoinsMe2;
    @BindView(R.id.fragment_coins_me3)
    ItemViewGroup fragmentCoinsMe3;
    @BindView(R.id.fragment_coins_me4)
    ItemViewGroup fragmentCoinsMe4;
    @BindView(R.id.fragment_coins_me5)
    ItemViewGroup fragmentCoinsMe5;
    @BindView(R.id.tv_coins_treaty)
    TextView tvCoinsTreaty;
    @BindView(R.id.tv_coins_secret)
    TextView tvCoinsSecret;
    @BindView(R.id.iv_see_details)
    ImageView ivSeeDetails;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_coins;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MeAuditSuccessContract.Presenter initPresenter() {
        mPresenter = new MeAuditSuccessPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("零钱");
        fragmentCoinsMe1.setContent(R.drawable.recharge,"充值","",false);
        fragmentCoinsMe2.setContent(R.drawable.withdraw,"提现","",false);
        fragmentCoinsMe3.setContent(R.drawable.real_name,"实名认证","",false);
        fragmentCoinsMe4.setContent(R.drawable.bankcard,"我的银行卡","",false);
        fragmentCoinsMe5.setContent(R.drawable.safety_settings,"安全设置","",false);
    }

    private JSONObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.fragment_coins_me1, R.id.fragment_coins_me2, R.id.fragment_coins_me3,
            R.id.fragment_coins_me4, R.id.fragment_coins_me5, R.id.tv_coins_treaty, R.id.tv_coins_secret,R.id.iv_see_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.fragment_coins_me1://充值
                startActivity(new Intent(this,TopUpActivity.class));
                break;
            case R.id.fragment_coins_me2://提现
                startActivity(new Intent(this,WithdrawActivity.class));
                break;
            case R.id.fragment_coins_me3://实名认证
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{}");
                    showDialog();
                    mPresenter.meAuditSuccess(body);
                break;
            case R.id.fragment_coins_me4://我的银行卡
                startActivity(new Intent(this,GetMyBankCardActivity.class));
                break;
            case R.id.fragment_coins_me5://安全设置
                startActivity(new Intent(this,CoinsSafetySettingActivity.class));
                break;
            case R.id.tv_coins_treaty://用户协议
                break;
            case R.id.tv_coins_secret://隐私协议
                break;
            case R.id.iv_see_details://查看明细
                startActivity(new Intent(this,MeCoinsBillDetailsActivity.class));
                break;
        }
    }

    @Override
    public void meAuditSuccessSus(MeAuditSuccessModel model) {
        disMissDialog();
        if (model.getData().getCodeX() == 0) {
            startActivity(new Intent(this,MeNoAuditActivity.class));
        }else {
            Intent intent = new Intent();
            intent.putExtra("idcard",model.getData().getIdcard());
            intent.putExtra("name",model.getData().getName());
            intent.setClass(this,MeAuditSuccessActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getMyBankCardSus(GetMyBankCardModel model) {
        disMissDialog();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
