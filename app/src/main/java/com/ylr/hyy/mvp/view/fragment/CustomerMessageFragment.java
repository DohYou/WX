package com.ylr.hyy.mvp.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.view.activity.ChatActivity;

import butterknife.BindView;

import static com.ylr.hyy.MVPApplication.IM_SDK;

public class CustomerMessageFragment extends BaseFragment {
    @BindView(R.id.conversation_layout)
    ConversationLayout conversationLayout;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_friendmessage;
    }

    @Override
    protected void initViews(View view) {

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            conversationLayout.initDefault(2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        conversationLayout.initDefault(2);
    }

    @Override
    protected void initDatas() {
        conversationLayout.initDefault(2);
        conversationLayout.getConversationList().getListLayout().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo messageInfo) {
                Log.i(IM_SDK, "跳转: "+new Gson().toJson(messageInfo));

                ChatInfo chatInfo = new ChatInfo();
//                String chatName = messageInfo.getLastMessage().getTIMMessage().getConversation().getPeer();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(messageInfo.getId());
                chatInfo.setChatName("chatName");
                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("chatMsg", new Gson().toJson(chatInfo));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }
}
