package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 投诉界面
 */
public class ComplainActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.item_complain1)
    ItemViewGroup itemComplain1;
    @BindView(R.id.item_complain2)
    ItemViewGroup itemComplain2;
    @BindView(R.id.item_complain3)
    ItemViewGroup itemComplain3;
    @BindView(R.id.item_complain4)
    ItemViewGroup itemComplain4;
    @BindView(R.id.item_complain5)
    ItemViewGroup itemComplain5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_complain;
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
        tvTitleName.setText("投诉");
        itemComplain1.setContent(0,"发布不良信息对我造成骚扰","",true);
        itemComplain1.setContent(0,"存在欺诈行为","",true);
        itemComplain1.setContent(0,"存在侵权行为","",true);
        itemComplain1.setContent(0,"发布假冒产品","",true);
        itemComplain1.setContent(0,"冒充他人","",true);

    }



    @OnClick({R.id.iv_title_return, R.id.item_complain1, R.id.item_complain2, R.id.item_complain3, R.id.item_complain4, R.id.item_complain5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.item_complain1:
                break;
            case R.id.item_complain2:
                break;
            case R.id.item_complain3:
                break;
            case R.id.item_complain4:
                break;
            case R.id.item_complain5:
                break;
        }
    }
}
