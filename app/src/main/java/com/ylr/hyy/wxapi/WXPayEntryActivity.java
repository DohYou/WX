package com.ylr.hyy.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ylr.hyy.utils.ToastUtils;

import static com.ylr.hyy.MVPApplication.WXAPP_ID;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI (this, WXAPP_ID);
        api.handleIntent (getIntent (), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent (intent);
        setIntent (intent);
        api.handleIntent (intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    private static final String TAG = "WXPayEntryActivity";

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType () == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                //成功
                case 0:
                    ToastUtils.showToast("支付成功");
                    break;
                default:
                    Log.i(TAG, "onResp: "+resp.errCode);
                    ToastUtils.showToast("支付失败");
                    break;

            }
            //避免闪屏
            Message message = new Message();
          handler.sendMessageDelayed(message,500);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            WXPayEntryActivity.this.finish();
        }
    };
}
