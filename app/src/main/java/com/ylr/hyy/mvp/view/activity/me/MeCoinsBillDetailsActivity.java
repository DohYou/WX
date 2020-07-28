package com.ylr.hyy.mvp.view.activity.me;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.view.fragment.BillDetailsIncomeFragment;
import com.ylr.hyy.mvp.view.fragment.BillDetailsPayFragment;
import com.ylr.hyy.mvp.view.fragment.BillDetailsWithdrawFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * 我的零钱 账单明细
 */
public class MeCoinsBillDetailsActivity extends BaseActivity {


    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindViews({R.id.tv_billdetails_income3,R.id.tv_billdetails_pay3,R.id.tv_billdetails_withdraw3})
    List<TextView>tvs;
    @BindView(R.id.fl_billdetails_income)
    FrameLayout flBilldetailsIncome;
    @BindViews({R.id.view_income,R.id.view_pay,R.id.view_withdraw})
    List<View>views;
    @BindView(R.id.fl_billdetails_pay)
    FrameLayout flBilldetailsPay;
    @BindView(R.id.fl_billdetails_withdraw)
    FrameLayout flBilldetailsWithdraw;
    @BindView(R.id.billdetails_f1_content)
    FrameLayout billdetailsF1Content;

    List<Fragment>list;
    FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me_coins_withdraw_billdetails;
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
        tvTitleName.setText("账单明细");

        list = new ArrayList<>();

        BillDetailsIncomeFragment billDetailsIncomeFragment = new BillDetailsIncomeFragment();
        list.add(billDetailsIncomeFragment);
        list.add(new BillDetailsPayFragment());
        list.add(new BillDetailsWithdrawFragment());

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.billdetails_f1_content, billDetailsIncomeFragment).show(billDetailsIncomeFragment).commit();
    }
    public void ChangeFragmentPage(int pos){
        FragmentTransaction transaction = manager.beginTransaction();

        if (!list.get(pos).isAdded()) {
            transaction.add(R.id.billdetails_f1_content,list.get(pos));
        }
        for (int i = 0; i <list.size() ; i++) {
            if (pos==i) {
                transaction.show(list.get(i));
                tvs.get(i).setTextColor(Color.parseColor("#237EFE"));
                views.get(i).setVisibility(View.VISIBLE);
            }
            else {
                transaction.hide(list.get(i));
                tvs.get(i).setTextColor(Color.parseColor("#333333"));
                views.get(i).setVisibility(View.INVISIBLE);
            }
        }
        transaction.commit();
    }



    @OnClick({R.id.iv_title_return, R.id.fl_billdetails_income, R.id.fl_billdetails_pay, R.id.fl_billdetails_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.fl_billdetails_income:
                ChangeFragmentPage(0);
                break;
            case R.id.fl_billdetails_pay:
                ChangeFragmentPage(1);
                break;
            case R.id.fl_billdetails_withdraw:
                ChangeFragmentPage(2);
                break;
        }
    }
}
