package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InactiveQMembersActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_inactive_1)
    TextView tvInactive1;
    @BindView(R.id.rl_inactive_threedays)
    RelativeLayout rlInactiveThreedays;
    @BindView(R.id.tv_inactive_2)
    TextView tvInactive2;
    @BindView(R.id.rl_inactive_aweek)
    RelativeLayout rlInactiveAweek;
    @BindView(R.id.tv_inactive_3)
    TextView tvInactive3;
    @BindView(R.id.rl_inactive_amonth)
    RelativeLayout rlInactiveAmonth;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inactiveqmembers;
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
        tvTitleName.setText("不活跃群成员");

    }


    @OnClick({R.id.iv_title_return, R.id.rl_inactive_threedays, R.id.rl_inactive_aweek, R.id.rl_inactive_amonth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.rl_inactive_threedays:
                startActivity(new Intent(this,InactiveThreedaysActivity.class));
                break;
            case R.id.rl_inactive_aweek:
                startActivity(new Intent(this,InactiveAWeekActivity.class));
                break;
            case R.id.rl_inactive_amonth:
                startActivity(new Intent(this,InactiveAMonthActivity.class));
                break;
        }
    }
}
