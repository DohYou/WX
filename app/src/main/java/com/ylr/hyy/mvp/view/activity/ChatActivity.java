package com.ylr.hyy.mvp.view.activity;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import java.lang.reflect.Type;

import butterknife.BindView;

//聊天页面
public class ChatActivity extends BaseActivity {
    @BindView(R.id.chat_layout)
    ChatLayout chatLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initWindow() {

    }

    private static final String TAG = "ChatActivity";
    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        // 单聊面板的默认 UI 和交互初始化
        chatLayout.initDefault();
        chatLayout.setOnRightListener(() -> {
            Log.i(TAG, "initDatas: ");
            Intent intent = new Intent();
//            intent.setClass()
        });
        // 传入 ChatInfo 的实例，这个实例必须包含必要的聊天信息，一般从调用方传入
        // 构造 mChatInfo 可参考 StartC2CChatActivity.java 的方法 startConversation
        String data = getIntent().getStringExtra("chatMsg");
        Type type = new TypeToken<ChatInfo>() {}.getType();
        ChatInfo chatInfo = new Gson().fromJson(data, type);
        chatLayout.setChatInfo(chatInfo);
    }
}
