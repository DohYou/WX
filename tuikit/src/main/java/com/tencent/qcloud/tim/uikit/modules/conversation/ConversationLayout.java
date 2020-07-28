package com.tencent.qcloud.tim.uikit.modules.conversation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.qcloud.tim.uikit.R;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationLayout;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.lang.reflect.Type;

public class ConversationLayout extends RelativeLayout implements IConversationLayout {
    public static final String IM_SDK = "TXIM";//区分腾讯im日志

    private TitleBarLayout mTitleBarLayout;
    private ConversationListLayout mConversationList;



    public ConversationLayout(Context context) {
        super(context);
        init();
    }

    public ConversationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConversationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private static final String TAG = "ConversationLayout";
    /**
     * 初始化相关UI元素
     */
    private void init() {
        inflate(getContext(), R.layout.conversation_layout, this);
        mTitleBarLayout = findViewById(R.id.conversation_title);
        mConversationList = findViewById(R.id.conversation_list);
    }

    /**
     *
     * @param msgType  1好友消息  2客户消息  3群消息
     */
    public void initDefault(final int msgType) {
        mTitleBarLayout.setTitle(getResources().getString(R.string.conversation_title), TitleBarLayout.POSITION.MIDDLE);
        mTitleBarLayout.getLeftGroup().setVisibility(View.GONE);
        mTitleBarLayout.setRightIcon(R.drawable.conversation_more);

        final IConversationAdapter adapter = new ConversationListAdapter();
        mConversationList.setAdapter(adapter);
        ConversationManagerKit.getInstance().loadConversation(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                String model = new Gson().toJson(data);
                Log.i(IM_SDK, "onSuccess: "+model);
                Type type = new TypeToken<ConversationProvider>() {}.getType();
                ConversationProvider base = new Gson().fromJson(model, type);
                for (int i = 0; i < base.getDataSource().size(); i++) {
                    Log.i(IM_SDK, "第"+i+"条数据= " + new Gson().toJson(base.getDataSource().get(i)));
                    if (msgType == 1) {//是好友消息就把群消息和客户消息删除掉
                        if (base.getDataSource().get(i).isGroup() || base.getDataSource().get(i).getLastMessage() != null
                                && base.getDataSource().get(i).getLastMessage().getTIMMessage() != null
                                && base.getDataSource().get(i).getLastMessage().getTIMMessage().getConversation() != null
                                && !base.getDataSource().get(i).getLastMessage().getTIMMessage().getConversation().getPeer().equals("735200")) {
                            //这里我们要的是好友消息
                            //我们isGroup代表是否是群消息
                            //如果是   那么我们把这条消息删了
                            //deleteConversation   删除了之后这条消息就不见了
                            //123456    当i是2的时候会把第3条删除  那么第4条就会
                            //变成第3条    因为没有第3条了1 1
                            //然后当下次循环的时候   i是3   那么12456取出来的是第5条数据
                            //然后就会变成第4条没有判断  会漏掉他
                            base.deleteConversation(i);
                            //所以这个地方只要删除了一条数据 i就减1   重新判断当前这条数据
                            i--;
                        }
                    }else if (msgType == 2) {//是客户消息 就把……
                        if (base.getDataSource().get(i).isGroup() || base.getDataSource().get(i).getLastMessage() != null
                                && base.getDataSource().get(i).getLastMessage().getTIMMessage() != null
                                && base.getDataSource().get(i).getLastMessage().getTIMMessage().getConversation() != null
                                && base.getDataSource().get(i).getLastMessage().getTIMMessage().getConversation().getPeer().equals("735200")) {
                            //这里我们要的是好友消息
                            //我们isGroup代表是否是群消息
                            //如果是   那么我们把这条消息删了
                            //deleteConversation   删除了之后这条消息就不见了
                            //123456    当i是2的时候会把第3条删除  那么第4条就会
                            //变成第3条    因为没有第3条了1 1
                            //然后当下次循环的时候   i是3   那么12456取出来的是第5条数据
                            //然后就会变成第4条没有判断  会漏掉他
                            base.deleteConversation(i);
                            //所以这个地方只要删除了一条数据 i就减1   重新判断当前这条数据
                            i--;
                        }
                    }else if (msgType == 3) {//是群聊消息 就把……
                        if (!base.getDataSource().get(i).isGroup()) {
                            base.deleteConversation(i);
                            i--;
                        }
                    }else {

                    }
                }
                //剩下的消息就是当前类型需要的消息 msgType
                //区分出消息类型之后在更新消息列表
                adapter.setDataProvider(base);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage("加载消息失败");
            }
        });
    }

    public TitleBarLayout getTitleBar() {
        return mTitleBarLayout;
    }

    @Override
    public void setParentLayout(Object parent) {

    }

    @Override
    public ConversationListLayout getConversationList() {
        return mConversationList;
    }

    public void addConversationInfo(int position, ConversationInfo info) {
        mConversationList.getAdapter().addItem(position, info);
    }

    public void removeConversationInfo(int position) {
        mConversationList.getAdapter().removeItem(position);

    }

    @Override
    public void setConversationTop(int position, ConversationInfo conversation) {
        Log.i(IM_SDK, "setConversationTop: "+new Gson().toJson(conversation));
        ConversationManagerKit.getInstance().setConversationTop(position, conversation);
    }

    @Override
    public void deleteConversation(int position, ConversationInfo conversation) {
        ConversationManagerKit.getInstance().deleteConversation(position, conversation);
    }
}
