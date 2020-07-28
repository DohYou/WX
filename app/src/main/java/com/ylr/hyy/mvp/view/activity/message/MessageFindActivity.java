package com.ylr.hyy.mvp.view.activity.message;

import android.view.View;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息 搜索界面
 */
public class MessageFindActivity extends BaseActivity {

    @BindView(R.id.message_find_cancel)
    TextView messageFindCancel;
    @BindView(R.id.tv_msg_find_friend)
    TextView tvMsgFindFriend;
    @BindView(R.id.tv_msg_find_customer)
    TextView tvMsgFindCustomer;
    @BindView(R.id.tv_msg_find_qpeople)
    TextView tvMsgFindQpeople;
    @BindView(R.id.tv_msg_find_talk)
    TextView tvMsgFindTalk;
    @BindView(R.id.tv_msg_find_friendq)
    TextView tvMsgFindFriendq;
    @BindView(R.id.tv_msg_find_customerq)
    TextView tvMsgFindCustomerq;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_find;
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

    }



    @OnClick({R.id.message_find_cancel, R.id.tv_msg_find_friend, R.id.tv_msg_find_customer, R.id.tv_msg_find_qpeople, R.id.tv_msg_find_talk, R.id.tv_msg_find_friendq, R.id.tv_msg_find_customerq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_find_cancel:
                finish();
                break;
            case R.id.tv_msg_find_friend:
                break;
            case R.id.tv_msg_find_customer:
                break;
            case R.id.tv_msg_find_qpeople:
                break;
            case R.id.tv_msg_find_talk:
                break;
            case R.id.tv_msg_find_friendq:
                break;
            case R.id.tv_msg_find_customerq:
                break;
        }
    }
}
