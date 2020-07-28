package com.ylr.hyy.mvp.view.activity.me;

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

public class MeCollectionAddActivity extends BaseActivity {


    @BindView(R.id.iv_mecollectionadd_back)
    ImageView ivMecollectionaddBack;
    @BindView(R.id.tv_mecollectionadd_save)
    TextView tvMecollectionaddSave;
    @BindView(R.id.tv_collectionadd_title)
    TextView tvCollectionaddTitle;
    @BindView(R.id.tv_collectionadd_price)
    TextView tvCollectionaddPrice;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.tv_location_name)
    TextView tvLocationName;
    @BindView(R.id.iv_mecollectionadd_photo)
    ImageView ivMecollectionaddPhoto;
    @BindView(R.id.iv_collectionadd_takephoto)
    ImageView ivCollectionaddTakephoto;
    @BindView(R.id.iv_collectionadd_locate)
    ImageView ivCollectionaddLocate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mecollectionadd;
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



    @OnClick({R.id.iv_mecollectionadd_back, R.id.tv_mecollectionadd_save, R.id.iv_mecollectionadd_photo, R.id.iv_collectionadd_takephoto, R.id.iv_collectionadd_locate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mecollectionadd_back:
                finish();
                break;
            case R.id.tv_mecollectionadd_save:
                break;
            case R.id.iv_mecollectionadd_photo:
                break;
            case R.id.iv_collectionadd_takephoto:
                break;
            case R.id.iv_collectionadd_locate:
                break;
        }
    }
}
