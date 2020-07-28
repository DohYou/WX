package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.SearchFriendContract;
import com.ylr.hyy.mvp.model.SearchFriendModel;
import com.ylr.hyy.mvp.presenter.SearchFriendPresenter;
import com.ylr.hyy.mvp.view.activity.MainActivity;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class AddFriendActivity extends BaseActivity {
    @BindView(R.id.iv_addfriend_back)
    ImageView ivTitleReturn;
    @BindView(R.id.rl_addfriend_scan)
    RelativeLayout rlAddfriendScan;
    @BindView(R.id.rl_addfriend_phone)
    RelativeLayout rlAddfriendPhone;
    @BindView(R.id.fl_addfriend)
    FrameLayout flAddfriend;
    @BindView(R.id.fl_addfriend_addfriend)
    FrameLayout flAddfriendAddfriend;
    @BindView(R.id.view_addfriend)
    View viewAddfriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addfriend;
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

    @OnClick({R.id.iv_addfriend_back, R.id.rl_addfriend_scan,R.id.rl_addfriend_phone, R.id.et_addfriend_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_addfriend_back:
                finish();
                break;
            case R.id.rl_addfriend_scan:
                break;
            case R.id.rl_addfriend_phone:
                break;
            case R.id.et_addfriend_search:
                startActivity(new Intent(this,SearchFriendActivity.class));
                finish();
                break;
        }
    }
}
