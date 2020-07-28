package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的 设置 通用
 */

public class MeSettingCurrencyActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_setting_currency1)
    FrameLayout meSettingCurrency1;
    @BindView(R.id.me_setting_currency2)
    FrameLayout meSettingCurrency2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mesettingcurrency;
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
        tvTitleName.setText("通用");

    }



    @OnClick({R.id.iv_title_return, R.id.me_setting_currency1, R.id.me_setting_currency2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.me_setting_currency1://字体大小
                break;
            case R.id.me_setting_currency2://一键清除聊天记录
                break;
        }
    }
}
