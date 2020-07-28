package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.view.activity.login.LoginActivity;
import com.ylr.hyy.mvp.view.dialog.DefaultDialog;
import com.ylr.hyy.mvp.view.dialog.UpAppDialog;
import com.ylr.hyy.mvp.view.activity.me.MeSettingSafetyActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的 设置
 */
public class MeSettingActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.me_setting1)
    FrameLayout meSetting1;
    @BindView(R.id.me_setting2)
    FrameLayout meSetting2;
    @BindView(R.id.me_setting3)
    FrameLayout meSetting3;
    @BindView(R.id.me_setting4)
    FrameLayout meSetting4;
    @BindView(R.id.me_setting5)
    FrameLayout meSetting5;
    @BindView(R.id.me_setting6)
    FrameLayout meSetting6;
    @BindView(R.id.me_setting7)
    FrameLayout meSetting7;
    @BindView(R.id.tv_loginout)
    TextView tvLoginOut;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
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
        tvTitleName.setText("设置");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    private static final String TAG = "MeSettingActivity";
    @OnClick({R.id.iv_title_return, R.id.me_setting1, R.id.me_setting2, R.id.me_setting3, R.id.me_setting4, R.id.me_setting5, R.id.me_setting6, R.id.me_setting7, R.id.tv_loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.me_setting1://安全
                startActivity(new Intent(this, MeSettingSafetyActivity.class));
                break;
            case R.id.me_setting2://新消息设置
                break;
            case R.id.me_setting3://隐私
                startActivity(new Intent(this,MeSettingPrivacyActivity.class));
                break;
            case R.id.me_setting4://通用
                startActivity(new Intent(this,MeSettingCurrencyActivity.class));
                break;
            case R.id.me_setting5://清除缓存
                break;
            case R.id.me_setting6://版本更新
                UpAppDialog dialog = new UpAppDialog();
                dialog.setUpContent("发现新版本\n1。*******\n2.*******\nhhhhhhhh");
                dialog.setListener(new UpAppDialog.OnDownListener() {
                    @Override
                    public void down() {

                    }
                });
                dialog.show(getSupportFragmentManager(),"");
                break;
            case R.id.me_setting7://关于我们
                break;
            case R.id.tv_loginout://退出登录
                new DefaultDialog.Builder().tipsContent("确认退出登录？").onSureClickListener(new DefaultDialog.OnSureClickListener() {
                    @Override
                    public void sure() {
                        SPUtils.getInstance().clear();
                        Intent intent = new Intent();
                        intent.setClass(MeSettingActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        MeSettingActivity.this.finish();
                    }

                    @Override
                    public void cancel() {

                    }
                }).build().show(getSupportFragmentManager(),"");
                break;
        }
    }
}
