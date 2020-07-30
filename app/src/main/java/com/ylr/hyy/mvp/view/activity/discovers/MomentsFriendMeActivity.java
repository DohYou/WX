package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的朋友圈
 */
public class MomentsFriendMeActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_friendmomentsme_header)
    ImageView ivFriendmomentsmeHeader;
    @BindView(R.id.tv_friendmomentsme_name)
    TextView tvFriendmomentsmeName;
    @BindView(R.id.tv_friendmomentsme_sign)
    TextView tvFriendmomentsmeSign;
    @BindView(R.id.iv_friendmomentsme_sex)
    ImageView ivFriendmomentsmeSex;
    @BindView(R.id.iv_friendmomentsme_memberlevel)
    ImageView ivFriendmomentsmeMemberlevel;
    @BindView(R.id.tv_friendmomentsme_membertext)
    TextView tvFriendmomentsmeMembertext;
    @BindView(R.id.rv_friendme)
    RecyclerView rvFriendMe;
    @BindView(R.id.iv_friendqme_sendpyq)
    ImageView ivFriendqmeSendpyq;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moments_mefriend;
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




    @OnClick({R.id.iv_title_return, R.id.iv_friendqme_sendpyq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_friendqme_sendpyq:
                Intent intent = new Intent();
                intent.putExtra("iscircle",0);
                intent.setClass(this,SendMomentsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
