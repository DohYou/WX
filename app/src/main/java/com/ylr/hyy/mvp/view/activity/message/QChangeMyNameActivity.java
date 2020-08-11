package com.ylr.hyy.mvp.view.activity.message;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

public class QChangeMyNameActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_qchangemyname_originname)
    TextView tvQchangemynameOriginname;
    @BindView(R.id.iv_qchangename_myheader)
    ImageView ivQchangenameMyheader;
    @BindView(R.id.et_qchange_myname)
    EditText etQchangeMyname;
    @BindView(R.id.iv_qchangemyname_delete)
    ImageView ivQchangemynameDelete;
    @BindView(R.id.tv_qchangemyname_sure)
    TextView tvQchangemynameSure;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qchangemyname;
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






    @OnClick({R.id.iv_title_return, R.id.iv_qchangemyname_delete, R.id.tv_qchangemyname_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_qchangemyname_delete:
                break;
            case R.id.tv_qchangemyname_sure:

                break;
        }
    }
}
