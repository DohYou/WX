package com.ylr.hyy.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.view.activity.message.MessageFindActivity;
import com.ylr.hyy.mvp.view.dialog.MessageMenuDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment {
    @BindView(R.id.message_find)
    ImageView messageFind;
    @BindView(R.id.message_add)
    ImageView messageAdd;
    @BindView(R.id.fl_friend_msg)
    FrameLayout flFriendMsg;
    @BindView(R.id.fl_customer_msg)
    FrameLayout flCustomerMsg;
    @BindView(R.id.fl_people_msg)
    FrameLayout flPeopleMsg;
    @BindViews({R.id.tv_friendmsg, R.id.tv_customermsg, R.id.tv_peoplemsg})
    List<TextView> tvs;
    @BindView(R.id.friendmsg_f1content)
    FrameLayout friendmsgF1content;
    @BindViews({R.id.view_friendmsg,R.id.view_customermsg,R.id.view_peoplemsg})
    List<View>views;

    private List<Fragment> list;
    private FragmentManager manager;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initDatas() {
        list = new ArrayList<>();

        FriendMessageFragment friendMessageFragment = new FriendMessageFragment();
        list.add(friendMessageFragment);
        list.add(new CustomerMessageFragment());
        list.add(new PeopleMessageFragment());

        manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.friendmsg_f1content, friendMessageFragment).show(friendMessageFragment).commit();
    }

    public void ChangeFragmentPage(int pos) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (!list.get(pos).isAdded()) {
            transaction.add(R.id.friendmsg_f1content, list.get(pos));
        }
        for (int i = 0; i < list.size(); i++) {
            if (pos == i) {
                transaction.show(list.get(i));
                tvs.get(i).setTextColor(Color.parseColor("#237EFE"));
                views.get(i).setVisibility(View.VISIBLE);
            } else {
                transaction.hide(list.get(i));
                tvs.get(i).setTextColor(Color.parseColor("#333333"));
                views.get(i).setVisibility(View.INVISIBLE);
            }
        }
        transaction.commit();
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }


    private MessageMenuDialog messageMenuDialog = null;
    private long mExitTime;
    @OnClick({R.id.message_find, R.id.message_add, R.id.fl_friend_msg, R.id.fl_customer_msg, R.id.fl_people_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_find://消息 查找
                startActivity(new Intent(getActivity(), MessageFindActivity.class));
                break;
            case R.id.message_add://消息 添加
                if (messageMenuDialog == null) {
                    messageMenuDialog = new MessageMenuDialog();
                    messageMenuDialog.setOnGetCodeListener(() -> {

                    });
                }

                if ((System.currentTimeMillis() - mExitTime) > 500) {
                    messageMenuDialog.show(activity.getSupportFragmentManager(),"");
                    mExitTime = System.currentTimeMillis();
                }
                break;
            case R.id.fl_friend_msg: //好友消息
                ChangeFragmentPage(0);
                break;
            case R.id.fl_customer_msg://客户消息
                ChangeFragmentPage(1);
                break;
            case R.id.fl_people_msg://群聊消息
                ChangeFragmentPage(2);
                break;
        }
    }

}
