package com.ylr.hyy.mvp.view.fragment;

import android.content.Intent;
import android.view.View;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.view.activity.discovers.AiFinderActivity;
import com.ylr.hyy.mvp.view.activity.discovers.DiscoversUtilsActivity;
import com.ylr.hyy.mvp.view.activity.discovers.MomentsCustomerActivity;
import com.ylr.hyy.mvp.view.activity.me.MomentsFriendActivity;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

public class DiscoversFragment extends BaseFragment {
    @BindView(R.id.itm_discover1)
    ItemViewGroup itmDiscover1;
    @BindView(R.id.itm_discover2)
    ItemViewGroup itmDiscover2;
    @BindView(R.id.itm_discover3)
    ItemViewGroup itmDiscover3;
    @BindView(R.id.itm_discover4)
    ItemViewGroup itmDiscover4;
    @BindView(R.id.itm_discover5)
    ItemViewGroup itmDiscover5;
    @BindView(R.id.itm_discover6)
    ItemViewGroup itmDiscover6;
//    @BindView(R.id.itm_discover7)
//    ItemViewGroup itmDiscover7;
    @BindView(R.id.itm_discover8)
    ItemViewGroup itmDiscover8;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_discovers;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initDatas() {
        itmDiscover1.setContent(R.drawable.friend_gray,"好友动态","",false);
        itmDiscover2.setContent(R.drawable.home_customer,"客户动态","",false);
        itmDiscover3.setContent(R.drawable.mallgray,"会员商城","",false);
        itmDiscover4.setContent(R.drawable.gfmsg,"官方消息","",false);
        itmDiscover5.setContent(R.drawable.kf,"官方小助手","",false);
        itmDiscover6.setContent(R.drawable.box,"官方信箱","",false);
//        itmDiscover7.setContent(R.drawable.aigray,"AI助手","",false);
        itmDiscover8.setContent(R.drawable.tools,"实用小工具","",false);
    }
    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.itm_discover1, R.id.itm_discover2, R.id.itm_discover3, R.id.itm_discover4, R.id.itm_discover5,
            R.id.itm_discover6,R.id.itm_discover8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.itm_discover1://好友圈
                startActivity(new Intent(getActivity(), MomentsFriendActivity.class));
                break;
            case R.id.itm_discover2://客户圈
                startActivity(new Intent(getActivity(), MomentsCustomerActivity.class));
                break;
            case R.id.itm_discover3://会员商城
                break;
            case R.id.itm_discover4://官方消息
                break;
            case R.id.itm_discover5://官方小助手
                break;
            case R.id.itm_discover6://官方信箱
                break;
//            case R.id.itm_discover7://AI工具
//                startActivity(new Intent(activity, AiFinderActivity.class));
//                break;
            case R.id.itm_discover8://实用小工具
                startActivity(new Intent(getActivity(), DiscoversUtilsActivity.class));
                break;
        }
    }
}
