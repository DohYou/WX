package com.ylr.hyy.mvp.view.activity.me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.ChooseMemberPayContract;
import com.ylr.hyy.mvp.model.WXPayModel;
import com.ylr.hyy.mvp.model.ZFBPayModel;
import com.ylr.hyy.mvp.presenter.ChooseMemberPayPresenter;
import com.ylr.hyy.utils.PayResult;
import com.ylr.hyy.utils.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class ChooseMemberPayActivity extends BaseActivity<ChooseMemberPayContract.View,ChooseMemberPayContract.Presenter> implements ChooseMemberPayContract.View {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_wechatpay)
    ImageView ivWechatpay;
    @BindView(R.id.rl_wechatpay)
    RelativeLayout rlWechatpay;
    @BindView(R.id.iv_zfb)
    ImageView ivZfb;
    @BindView(R.id.rl_zfbpay)
    RelativeLayout rlZfbpay;
    @BindView(R.id.pay_immediately)
    TextView payImmediately;
    @BindView(R.id.iv_wechatpayok)
    ImageView ivWechatpayok;
    @BindView(R.id.ivzfbpayok)
    ImageView ivzfbpayok;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choosememberpay;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected ChooseMemberPayContract.Presenter initPresenter() {
        mPresenter = new ChooseMemberPayPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("支付方式");

    }


    private int payType; //1 支付宝  2 微信
    @OnClick({R.id.iv_title_return, R.id.rl_wechatpay, R.id.rl_zfbpay, R.id.pay_immediately})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.rl_wechatpay:
                payType = 2;
                ivWechatpayok.setVisibility(View.VISIBLE);
                ivzfbpayok.setVisibility(View.INVISIBLE);
                break;
            case R.id.rl_zfbpay:
                payType = 1;
                ivzfbpayok.setVisibility(View.VISIBLE);
                ivWechatpayok.setVisibility(View.INVISIBLE);
                break;
            case R.id.pay_immediately:
                if (payType != 0) {
                    showDialog();
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("vipid",getIntent().getStringExtra("vipID"));
                    jsonObject.addProperty("mold","1");
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    if (payType == 1) {
                        mPresenter.zfb(body);
                    }else {
                        mPresenter.wx(body);
                    }
                }else {
                    ToastUtils.showToast("请选择支付方式");
                }
                break;
        }
    }

    @Override
    public void wxSus(WXPayModel model) {
        disMissDialog();
        toWXPay(model);
    }

    private void toWXPay(WXPayModel wxBean) {
        IWXAPI iwxapi = WXAPIFactory.createWXAPI(this, null); //初始化微信api
        iwxapi.registerApp(wxBean.getData().getAppid()); //注册appid  appid可以在开发平台获取

        PayReq request = new PayReq(); //调起微信APP的对象
        request.appId = wxBean.getData().getAppid();
        request.partnerId = wxBean.getData().getPartnerid();
        request.prepayId = wxBean.getData().getPrepayid();
        request.packageValue = wxBean.getData().getPackageX();
        request.nonceStr = wxBean.getData().getNoncestr();
        request.timeStamp = wxBean.getData().getTimestamp();
        request.sign = wxBean.getData().getSign();
        iwxapi.sendReq(request);//发送调起微信的请求
    }

    @Override
    public void zfbSus(ZFBPayModel model) {
        disMissDialog();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask payTask = new PayTask(ChooseMemberPayActivity.this);
                Map<String,String> result = payTask.payV2(model.getData(),true);
                Message msg = new Message();
                msg.what = 0x110;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x110: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showToast("支付成功");
                    } else {
                        ToastUtils.showToast("支付失败");
                    }
                }
            }
        };
    };

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
