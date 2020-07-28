package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

public class PeopleSendHelperActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_peoplesend_instruction)
    TextView tvInstruction;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_peoplesendhelper;
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

    @OnClick({R.id.iv_title_return,R.id.tv_peoplesend_instruction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_peoplesend_instruction:
                startActivity(new Intent(this,PeopleSendInstructionActivity.class));
                break;

        }
    }
}
