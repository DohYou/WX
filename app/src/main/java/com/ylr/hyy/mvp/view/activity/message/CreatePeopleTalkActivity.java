package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatePeopleTalkActivity extends BaseActivity {
    @BindView(R.id.iv_changename_back)
    ImageView ivChangenameBack;
    @BindView(R.id.iv_createfqtalk)
    ImageView ivCreatefqtalk;
    @BindView(R.id.iv_createcqtalk)
    ImageView ivCreatecqtalk;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_createpeopletalk;
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


    @OnClick({R.id.iv_changename_back,R.id.iv_createfqtalk, R.id.iv_createcqtalk})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_changename_back:
                finish();
                break;
            case R.id.iv_createfqtalk:
                intent.putExtra("type",0);
                intent.setClass(this,CreateGroupActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_createcqtalk:
                intent.putExtra("type",1);
                intent.setClass(this,CreateGroupActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
