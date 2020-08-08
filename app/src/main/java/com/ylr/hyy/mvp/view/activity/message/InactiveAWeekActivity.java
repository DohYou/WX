package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InactiveAWeekActivity extends BaseActivity {
    @BindView(R.id.iv_inactiveweek_back)
    ImageView ivInactiveweekBack;
    @BindView(R.id.tv_inactiveweek_dchoose)
    TextView tvInactiveweekDchoose;
    @BindView(R.id.rv_inactive_week)
    RecyclerView rvInactiveWeek;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inactiveaweek;
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

    }


    @OnClick({R.id.iv_inactiveweek_back, R.id.tv_inactiveweek_dchoose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_inactiveweek_back:
                finish();
                break;
            case R.id.tv_inactiveweek_dchoose:
                break;
        }
    }
}
