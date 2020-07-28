package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.friendship.TIMFriend;
import com.tencent.imsdk.friendship.TIMFriendRequest;
import com.tencent.imsdk.friendship.TIMFriendResult;
import com.tencent.imsdk.v2.V2TIMFriendAddApplication;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseCallBack;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.http.HttpService;
import com.ylr.hyy.http.SendRequest;
import com.ylr.hyy.mvp.contract.NoFriendContract;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.mvp.presenter.NoFriendPresenter;
import com.ylr.hyy.utils.RoundImageView;
import com.ylr.hyy.utils.ToastUtils;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class NoFriendActivity extends BaseActivity {
    @BindView(R.id.riv_no_friend_head)
    RoundImageView rivNoFriendHead;
    @BindView(R.id.tv_no_friend_name)
    TextView tvNoFriendName;
    @BindView(R.id.tv_no_friend_id)
    TextView tvNoFriendId;
    @BindView(R.id.iv_no_friend_sex)
    ImageView ivNoFriendSex;
    @BindView(R.id.iv_member1)
    ImageView ivMember1;
    @BindView(R.id.tv_no_friend_vip_lv)
    TextView tvNoFriendVipLv;
    @BindView(R.id.rl_fragment_me_header_personal)
    RelativeLayout rlFragmentMeHeaderPersonal;

    private String friendid;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_no_friend;
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
        Type type = new TypeToken<FriendModel>() {}.getType();
        FriendModel model = new Gson().fromJson(getIntent().getStringExtra("model"), type);
        if (model != null) {
            Glide.with(this).load(model.getData().getHeadimgurl()).into(rivNoFriendHead);
            tvNoFriendName.setText(model.getData().getNickname());
            tvNoFriendId.setText("用户id："+model.getData().getOnlyid() + "\n汇集号："+model.getData().getOnlyaccount());
            switch (model.getData().getVipgrade()) {
                case 0:
                    tvNoFriendVipLv.setText("普通用户");
                    break;
                case 1:
                    tvNoFriendVipLv.setText("vip会员");
                    break;
                case 2:
                    tvNoFriendVipLv.setText("超级会员");
                    break;
            }
            friendid = model.getData().getUid()+"";
        }
    }

    @OnClick({R.id.iv_no_friend_return, R.id.iv_diandian, R.id.tv_no_friend_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_no_friend_return:
                finish();
                break;
            case R.id.iv_diandian:
                break;
            case R.id.tv_no_friend_add:
                if (manager == null) {
                    manager = TIMFriendshipManager.getInstance();
                    manager.init();
                }
                V2TIMFriendAddApplication v2TIMFriendAddApplication = new V2TIMFriendAddApplication(friendid);
                v2TIMFriendAddApplication.setAddWording("你好");
                v2TIMFriendAddApplication.setAddSource("phone");
                V2TIMManager.getFriendshipManager().addFriend(v2TIMFriendAddApplication, new V2TIMValueCallback<V2TIMFriendOperationResult>() {
                    @Override
                    public void onError(int code, String desc) {
                        ToastUtils.showToast("Error code = " + code + ", desc = " + desc);
                    }

                    @Override
                    public void onSuccess(V2TIMFriendOperationResult v2TIMFriendOperationResult) {
                        switch (v2TIMFriendOperationResult.getResultCode()) {
                            case BaseConstants.ERR_SUCC:
                                ToastUtil.toastShortMessage("成功");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_INVALID_PARAMETERS:
                                if (TextUtils.equals(v2TIMFriendOperationResult.getResultInfo(), "Err_SNS_FriendAdd_Friend_Exist")) {
                                    ToastUtil.toastShortMessage("对方已是您的好友");
                                    break;
                                }
                            case BaseConstants.ERR_SVR_FRIENDSHIP_COUNT_LIMIT:
                                ToastUtil.toastShortMessage("您的好友数已达系统上限");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_PEER_FRIEND_LIMIT:
                                ToastUtil.toastShortMessage("对方的好友数已达系统上限");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_IN_SELF_BLACKLIST:
                                ToastUtil.toastShortMessage("被加好友在自己的黑名单中");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_ALLOW_TYPE_DENY_ANY:
                                ToastUtil.toastShortMessage("对方已禁止加好友");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_IN_PEER_BLACKLIST:
                                ToastUtil.toastShortMessage("您已被被对方设置为黑名单");
                                break;
                            case BaseConstants.ERR_SVR_FRIENDSHIP_ALLOW_TYPE_NEED_CONFIRM:
                                ToastUtil.toastShortMessage("等待好友审核同意");
                                break;
                            default:
                                ToastUtil.toastLongMessage(v2TIMFriendOperationResult.getResultCode() + " " + v2TIMFriendOperationResult.getResultInfo());
                                break;
                        }
                        finish();
                    }
                });


                break;
        }
    }

    private TIMFriendshipManager manager = null;
    private static final String TAG = "NoFriendActivity";

}
