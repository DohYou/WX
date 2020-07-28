package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tencent.imsdk.v2.V2TIMFriendApplication;
import com.tencent.imsdk.v2.V2TIMFriendApplicationResult;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.VerifyMsgAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.NoFriendContract;
import com.ylr.hyy.mvp.presenter.NoFriendPresenter;
import com.ylr.hyy.utils.ToastUtils;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.tencent.imsdk.v2.V2TIMFriendApplication.V2TIM_FRIEND_APPLICATION_COME_IN;
import static com.ylr.hyy.MVPApplication.HttpType;

public class VerifyMsgActivity extends BaseActivity<NoFriendContract.View, NoFriendContract.Presenter> implements NoFriendContract.View {
    @BindView(R.id.iv_verifymsg)
    ImageView ivVerifyMsg;
    @BindView(R.id.rv_verifymsg)
    RecyclerView rvVerifyMsg;

    private VerifyMsgAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_verifymsg;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected NoFriendContract.Presenter initPresenter() {
        mPresenter = new NoFriendPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }


    private static final String TAG = "VerifyMsgActivity";
    private int pos;

    @Override
    protected void initDatas() {
        V2TIMManager.getFriendshipManager().getFriendApplicationList(new V2TIMValueCallback<V2TIMFriendApplicationResult>() {
            @Override
            public void onError(int code, String desc) {
                ToastUtil.toastShortMessage("Error code = " + code + ", desc = " + desc);
                Log.i(TAG, "onError: ");
                disMissDialog();
            }

            @Override
            public void onSuccess(V2TIMFriendApplicationResult v2TIMFriendApplicationResult) {
                disMissDialog();
                Log.i(TAG, "onSuccess: ");
                if (v2TIMFriendApplicationResult.getFriendApplicationList() != null) {
                    if (v2TIMFriendApplicationResult.getFriendApplicationList().size() == 0) {
                        ToastUtils.showToast("暂无好友申请");
                        return;
                    }
                }
                adapter.setList(v2TIMFriendApplicationResult.getFriendApplicationList());
                Log.i(TAG, "有多少条好友申请: " + v2TIMFriendApplicationResult.getFriendApplicationList().size());

            }
        });

        Type type = new TypeToken<List<V2TIMFriendApplication>>() {
        }.getType();
        List<V2TIMFriendApplication> model = new Gson().fromJson(getIntent().getStringExtra("model"), type);
        Log.i(TAG, "initDatas: " + new Gson().toJson(model));
        adapter = new VerifyMsgAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvVerifyMsg.setLayoutManager(manager);
        rvVerifyMsg.setAdapter(adapter);

        adapter.setOnAgreeListener(new VerifyMsgAdapter.OnAgreeListener() {
            @Override
            public void agree(String type, V2TIMFriendApplication data, int pos) {
                //https://docs-1252463788.cos.ap-shanghai.myqcloud.com/im/classcom_1_1tencent_1_1imsdk_1_1v2_1_1V2TIMFriendApplication.html
                V2TIMManager.getFriendshipManager().acceptFriendApplication(data, V2TIM_FRIEND_APPLICATION_COME_IN, new V2TIMValueCallback<V2TIMFriendOperationResult>() {
                    @Override
                    public void onError(int i, String s) {
                        ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
                    }

                    @Override
                    public void onSuccess(V2TIMFriendOperationResult v2TIMFriendOperationResult) {
                        Log.i(TAG, "onSuccess: " + new Gson().toJson(v2TIMFriendOperationResult));
                        int origin = -1;
                        if (type.equals("AddSource_Type_phone")) {
                            origin = 0;
                        } else {
                            origin = 1;
                        }
                        // 0:通过手机号添加,1:通过扫码添加,2:通过群聊添加,3:通过富集号添加:4通过名片添加
                        showDialog();
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("friendid", data.getUserID());
                        jsonObject.addProperty("origin", origin);
                        VerifyMsgActivity.this.pos = pos;
                        RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
                        mPresenter.addFriend(body);
                    }
                });

            }
        });

        Message message = new Message();
        message.what = 1;
        handler.sendMessageDelayed(message, 2000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            disMissDialog();
            ToastUtils.showToast("暂无好友申请");
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(1);
    }

    @Override
    public void addFriendSus(Base model) {
        disMissDialog();
        ToastUtils.showToast("添加成功");
        adapter.removeView(pos);
    }

    @Override
    public void showError(int code, String message) {
        ToastUtils.showToast(message);
        disMissDialog();
    }
    
    @OnClick({R.id.iv_verifymsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_verifymsg:
                finish();
                break;
        }
    }
}
