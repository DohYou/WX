package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发现 朋友圈
 */
public class MomentsFriendActivity extends BaseActivity {

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_friendmoments_header)
    ImageView ivFriendmomentsHeader;
    @BindView(R.id.tv_friendmoments_name)
    TextView tvFriendmomentsName;
    @BindView(R.id.tv_friendmoments_sign)
    TextView tvFriendmomentsSign;
    @BindView(R.id.iv_friendmoments_sex)
    ImageView ivFriendmomentsSex;
    @BindView(R.id.iv_friendmoments_memberlevel)
    ImageView ivFriendmomentsMemberlevel;
    @BindView(R.id.tv_friendmoments_membertext)
    TextView tvFriendmomentsMembertext;
    @BindView(R.id.rv_friend)
    RecyclerView rvFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moments_friend;
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

    @OnClick({R.id.iv_title_return, R.id.iv_friendmoments_header})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_friendmoments_header:
                break;
        }
    }
}
