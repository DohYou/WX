package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * 零钱安全设置 忘记密码 新密码
 */
public class CoinsSafetyForgetNewActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_forget_password_new_next)
    TextView tvForgetPasswordNewNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coins_safety_setting_forget_new;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }
    private String data;
    @Override
    protected void initDatas() {
        data = getIntent().getStringExtra("data");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("voucher",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_title_return, R.id.tv_forget_password_new_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_forget_password_new_next:
                break;
        }
    }
}
