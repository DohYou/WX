package com.ylr.hyy.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.base.BaseFragment;
import com.ylr.hyy.mvp.view.activity.ChatActivity;
import com.ylr.hyy.mvp.view.activity.login.LoginActivity;
import com.ylr.hyy.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;

import static com.ylr.hyy.MVPApplication.IM_SDK;

public class FriendMessageFragment extends BaseFragment implements TIMMessageListener {
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
            init();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    protected void initDatas() {
        showDialog();
        Log.i(TAG, "initDatas: "+SPUtils.getInstance().getInt("userId"));
        Log.i(TAG, "userSig: "+SPUtils.getInstance().getString("userSig"));

//        发起登录请求
        TIMManager.getInstance().login(SPUtils.getInstance().getInt("userId")+"",SPUtils.getInstance().getString("userSig"), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                disMissDialog();
                switch (i) {
                    case 6208:
                        ToastUtils.showToast("登录已失效，请重新登录");
                        startActivity(new Intent(activity, LoginActivity.class));
                        activity.finish();
                        break;
                    default:
                        ToastUtils.showToast("IM其他问题");
                        break;
                }
                Log.i(IM_SDK, "登录失败: "+s +"    状态码："+i);
            }

            @Override
            public void onSuccess() {
                Log.i(IM_SDK, "onSuccess: 登录成功");
                setUserListener();//用户信息变更回调
                addMessageListener();//新消息通知
                initIM();
                Message message = new Message();
                handler.sendMessageDelayed(message,1000);//登录成功之后延迟1秒再去获取消息列表
            }
        });
    }

    private static final String TAG = "FriendMessageFragment";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            disMissDialog();
            init();
        }
    };

    private void init(){
        String[] ids = SPUtils.getInstance().getString("friends").split(",");
        conversationLayout.initDefault(1,ids);//INIT  并且up消息列表 2
        //列表好友点击事件    跳转聊天记录
        conversationLayout.getConversationList().getListLayout().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo messageInfo) {
                Log.i(TAG, "onItemClick: "+new Gson().toJson(messageInfo));
                ChatInfo chatInfo = new ChatInfo();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(messageInfo.getId());
                chatInfo.setChatName(messageInfo.getTitle());
                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("chatMsg", new Gson().toJson(chatInfo));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * 用户状态变更
     */
    public void setUserListener() {
        TIMUserConfig config = new TIMUserConfig();
        config.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                //被踢下线时回调
                Log.i(IM_SDK, "被踢下线时回调: ");
                ToastUtils.showToast("已再其它设备登陆");
                SPUtils.getInstance().clear();
                Intent intent = new Intent();
                intent.setClass(activity, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                activity.finish();
            }

            @Override
            public void onUserSigExpired() {
                //票据过期时回调
                Log.i(IM_SDK, "票据过期时回调: ");


            }}).setConnectionListener(new TIMConnListener() {
            @Override
            public void onConnected() {
                Log.i(IM_SDK, "onConnected: ");
            }

            @Override
            public void onDisconnected(int i, String s) {
                Log.i(IM_SDK, "onDisconnected: "+s);
            }

            @Override
            public void onWifiNeedAuth(String s) {
                Log.i(IM_SDK, "onWifiNeedAuth: "+s);

            }
        });

        TIMManager.getInstance().setUserConfig(config);
    }

    /**
     * 新消息通知
     */
    public void addMessageListener() {
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {//消息监听器
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {//收到新消息
                for (int i = 0; i < list.size(); i++) {
                    TIMMessage msg = list.get(i);
                    if (msg.getConversation().getType().toString().equals("C2C")) {
                        //获取当前元素的类型
                        TIMElem elem = msg.getElement(0);
                        TIMElemType elemType = elem.getType();
                        if (elemType == TIMElemType.Custom) {
                            TIMCustomElem custom = (TIMCustomElem) elem;
                            try {
                                String s1 = new String(custom.getData(), "utf-8");
                                //TODO 新消息通知
                                Log.e(IM_SDK, "新消息通知---" + s1);
//                                EventBus.getDefault().post(new MessageEvent(s1));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                return false;
            }
        });

    }

    private void initIM() {
        TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
//开启离线推送
        settings.setEnabled(true);
//设置收到C2C离线消息时的提示声音，这里把声音文件放到了res/raw文件夹下
        settings.setC2cMsgRemindSound(null);
        TIMManager.getInstance().setOfflinePushSettings(settings);
    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        Log.i(IM_SDK, "onNewMessages: "+new Gson().toJson(list));
        return false;
    }
}
