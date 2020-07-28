package com.ylr.hyy.mvp.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.friendship.TIMFriend;
import com.tencent.imsdk.v2.V2TIMConversationResult;
import com.tencent.imsdk.v2.V2TIMFriendApplication;
import com.tencent.imsdk.v2.V2TIMFriendApplicationResult;
import com.tencent.imsdk.v2.V2TIMFriendshipManager;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.view.activity.ChatActivity;
import com.ylr.hyy.mvp.view.activity.message.VerifyMsgActivity;
import com.ylr.hyy.utils.ItemViewGroup;
import com.ylr.hyy.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class FriendFragment extends BaseFragment {
    private static final String TAG = "FriendFragment";
    @BindView(R.id.contact_layout)
    ContactLayout contactLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            init();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init(){
        contactLayout.initDefault();
        contactLayout.getContactListView().setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(activity, VerifyMsgActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        ChatInfo chatInfo = new ChatInfo();
                        chatInfo.setType(TIMConversationType.C2C);
                        chatInfo.setId(contact.getId());
                        String chatName = contact.getId();
                        if (!TextUtils.isEmpty(contact.getRemark())) {
                            chatName = contact.getRemark();
                        } else if (!TextUtils.isEmpty(contact.getNickname())) {
                            chatName = contact.getNickname();
                        }
                        chatInfo.setChatName(chatName);

                        intent.setClass(activity,ChatActivity.class);
                        intent.putExtra("chatMsg", new Gson().toJson(chatInfo));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                }
                Log.i(TAG, "onItemClick: "+position);
                Log.i(TAG, "ContactItemBean: "+new Gson().toJson(contact));
            }
        });
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

}
