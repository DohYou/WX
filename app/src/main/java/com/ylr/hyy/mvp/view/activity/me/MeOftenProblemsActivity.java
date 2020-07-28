package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
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
 * 我的 帮助 常见问题
 */
public class MeOftenProblemsActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.oftenproblems1)
    ItemViewGroup oftenproblems1;
    @BindView(R.id.oftenproblems2)
    ItemViewGroup oftenproblems2;
    @BindView(R.id.oftenproblems3)
    ItemViewGroup oftenproblems3;
    @BindView(R.id.oftenproblems4)
    ItemViewGroup oftenproblems4;
    @BindView(R.id.oftenproblems5)
    ItemViewGroup oftenproblems5;

    @Override
    protected int getLayoutId() {
        return R.layout.help_oftenproblems;
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
        oftenproblems1.setContent(0,"汇集云是什么？","",true);
        oftenproblems2.setContent(0,"如何联系汇集云？","",true);
        oftenproblems3.setContent(0,"提现银行卡怎么提现？？","",true);
        oftenproblems4.setContent(0,"提现银行卡可以切换银行卡吗？？","",true);
        oftenproblems5.setContent(0,"其他问题？","",true);

        tvTitleName.setText("帮助");

    }



    @OnClick({R.id.iv_title_return, R.id.oftenproblems1, R.id.oftenproblems2, R.id.oftenproblems3, R.id.oftenproblems4, R.id.oftenproblems5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.oftenproblems1:
                startActivity(new Intent(this, HelpOneActivity.class));
                break;
            case R.id.oftenproblems2:
                startActivity(new Intent(this, HelpTwoActivity.class));
                break;
            case R.id.oftenproblems3:
                startActivity(new Intent(this, HelpThreeActivity.class));
                break;
            case R.id.oftenproblems4:
                startActivity(new Intent(this, HelpFourActivity.class));
                break;
            case R.id.oftenproblems5:
                startActivity(new Intent(this, HelpFiveActivity.class));
                break;
        }
    }
}
