package com.ylr.hyy.mvp.view.activity.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ylr.hyy.R;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.utils.RoundImageView;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendNewsActivity extends BaseActivity {
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
    @BindView(R.id.iv_friend_news_set_friend)
    ImageView ivFriendNewsSetFriend;
    @BindView(R.id.iv_friend_news_set_customer)
    ImageView ivFriendNewsSetCustomer;
    @BindView(R.id.ll_friend_news_content)
    LinearLayout llFriendNewsContent;
    @BindView(R.id.tv_friend_beizhu)
    TextView tvBeiZhu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_news;
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
            if (model.getData().getSex() == 1) {
                ivNoFriendSex.setBackgroundResource(R.drawable.female);
            }else {
                ivNoFriendSex.setBackgroundResource(R.drawable.female);
            }
            tvBeiZhu.setText(model.getData().getRemarks());

        }
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.iv_friend_news_return, R.id.iv_friend_news_dian, R.id.fl_friend_news_set_beizhu, R.id.fl_friend_news_set_friend, R.id.fl_friend_news_set_customer, R.id.fl_friend_news_see_pengyouquan, R.id.fl_friend_news_all, R.id.tv_friend_news_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_friend_news_return:
                finish();
                break;
            case R.id.iv_friend_news_dian:
                break;
            case R.id.fl_friend_news_set_beizhu:
                break;
            case R.id.fl_friend_news_set_friend:
                break;
            case R.id.fl_friend_news_set_customer:
                break;
            case R.id.fl_friend_news_see_pengyouquan:
                break;
            case R.id.fl_friend_news_all:
                break;
            case R.id.tv_friend_news_send:
                break;
        }
    }
}
