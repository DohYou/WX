package com.ylr.hyy.mvp.view.fragment;

import android.view.View;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerFragment extends BaseFragment {
    @BindView(R.id.itm_customer1)
    ItemViewGroup itmCustomer1;
    @BindView(R.id.itm_customer2)
    ItemViewGroup itmCustomer2;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_customer;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initDatas() {
        itmCustomer1.setContent(R.drawable.fenlei,"分类","",false);
        itmCustomer2.setContent(R.drawable.phone_contact,"手机联系人","",false);
    }


    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.itm_customer1, R.id.itm_customer2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.itm_customer1:
                break;
            case R.id.itm_customer2:
                break;
        }
    }
}
