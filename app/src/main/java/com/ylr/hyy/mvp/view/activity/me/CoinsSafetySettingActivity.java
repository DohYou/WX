package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 零钱安全设置
 */
public class CoinsSafetySettingActivity extends BaseActivity {

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_coins_safety_setting1)
    ItemViewGroup meCoinsSafetySetting1;
    @BindView(R.id.me_coins_safety_setting2)
    ItemViewGroup meCoinsSafetySetting2;
    @BindView(R.id.me_coins_safety_setting3)
    ItemViewGroup meCoinsSafetySetting3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coins_safetysetting;
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

    @Override
    protected void initDatas() {
        tvTitleName.setText("安全设置");
        meCoinsSafetySetting1.setContent(0,"设置支付密码","",true);
        meCoinsSafetySetting2.setContent(0,"修改支付密码","",true);
        meCoinsSafetySetting3.setContent(0,"忘记支付密码","",true);

    }



    @OnClick({R.id.iv_title_return, R.id.me_coins_safety_setting1, R.id.me_coins_safety_setting2, R.id.me_coins_safety_setting3})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.me_coins_safety_setting1://设置支付密码
                intent.putExtra("type",1);
                intent.setClass(this,CoinsSafetySetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.me_coins_safety_setting2://修改支付密码
                intent.putExtra("type",2);
                intent.setClass(this,CoinsSafetySetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.me_coins_safety_setting3://忘记支付密码
                intent.putExtra("type",3);
                intent.setClass(this,CoinsSafetyForgetActivity.class);
                startActivity(intent);
                break;
        }
    }
}


