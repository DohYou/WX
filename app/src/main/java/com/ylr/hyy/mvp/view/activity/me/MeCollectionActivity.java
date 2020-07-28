package com.ylr.hyy.mvp.view.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeCollectionActivity extends BaseActivity {
    @BindView(R.id.iv_collection_back)
    ImageView ivCollectionBack;
    @BindView(R.id.fl_collectionadd)
    FrameLayout flCollectionadd;
    @BindView(R.id.collection_cancel)
    TextView collectionCancel;
    @BindView(R.id.tv_collection_pav)
    TextView tvCollectionPav;
    @BindView(R.id.tv_collection_note)
    TextView tvCollectionNote;
    @BindView(R.id.tv_collection_location)
    TextView tvCollectionLocation;
    @BindView(R.id.ll_mecollection)
    LinearLayout llMecollection;
    @BindView(R.id.tv_collection_talk)
    TextView tvCollectionTalk;
    @BindView(R.id.tv_collection_phonecall)
    TextView tvCollectionPhonecall;
    @BindView(R.id.tv_collection_file)
    TextView tvCollectionFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mecollection;
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



    @OnClick({R.id.iv_collection_back, R.id.fl_collectionadd, R.id.collection_cancel, R.id.tv_collection_pav, R.id.tv_collection_note, R.id.tv_collection_location, R.id.tv_collection_talk, R.id.tv_collection_phonecall, R.id.tv_collection_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_collection_back:
                finish();
                break;
            case R.id.fl_collectionadd:
                break;
            case R.id.collection_cancel:
                break;
            case R.id.tv_collection_pav:
                break;
            case R.id.tv_collection_note:
                break;
            case R.id.tv_collection_location:
                break;
            case R.id.tv_collection_talk:
                break;
            case R.id.tv_collection_phonecall:
                break;
            case R.id.tv_collection_file:
                break;
        }
    }
}
