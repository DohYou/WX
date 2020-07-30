package com.ylr.hyy.mvp.view.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.SizeUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.BeMemberAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MemberPriceContract;
import com.ylr.hyy.mvp.model.MemberPriceModel;
import com.ylr.hyy.mvp.presenter.MemberPricePresenter;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 开通会员
 */
public class BeMemberActivity extends BaseActivity<MemberPriceContract.View, MemberPriceContract.Presenter> implements MemberPriceContract.View {

    @BindView(R.id.iv_me_be_member_head)
    ImageView ivMeBeMemberHead;
    @BindView(R.id.be_member_immediately)
    TextView beMemberImmediately;
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_be_member_name)
    TextView tvBeMemberName;
    @BindView(R.id.iv_be_member_sex)
    ImageView ivBeMemberSex;
    @BindView(R.id.iv_be_member_level)
    ImageView ivBeMemberLevel;
    @BindView(R.id.tv_is_member_text)
    TextView tvIsMemberText;
    @BindView(R.id.tv_be_member_days)
    TextView tvBeMemberDays;
    @BindView(R.id.rv_be_member)
    RecyclerView rvBeMember;
    @BindView(R.id.tv_member_usebook)
    TextView tvMemberUseBook;
    @BindView(R.id.iv_notalwaysbuy)
    ImageView ivNotalwaysbuy;
    @BindView(R.id.iv_alwaysbuy)
    ImageView ivAlwaysbuy;

    private BeMemberAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_be_member;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MemberPriceContract.Presenter initPresenter() {
        mPresenter = new MemberPricePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    private int payId = -1;//选中的支付ID；

    @Override
    protected void initDatas() {

        showDialog();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mold", "1");
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            mPresenter.memberPrice(body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new BeMemberAdapter(this);
        rvBeMember.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        rvBeMember.addItemDecoration(new RVSpace(6,6,6,6));//item 距离
        rvBeMember.setAdapter(adapter);

        adapter.setOnSelectListener(new BeMemberAdapter.OnSelectListener() {
            @Override
            public void select(int id) {
                payId = id;
            }
        });
    }


    @OnClick({R.id.iv_title_return, R.id.be_member_immediately, R.id.tv_member_usebook,R.id.iv_alwaysbuy,R.id.iv_notalwaysbuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.be_member_immediately:
                if (payId == -1) {
                    ToastUtils.showToast("请选择会员类型");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("vipID", payId + "");
                intent.setClass(this, ChooseMemberPayActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_member_usebook:
                startActivity(new Intent(this, MemberUseBookActivity.class));
                break;
            case R.id.iv_alwaysbuy:
                ivAlwaysbuy.setVisibility(View.INVISIBLE);
                ivNotalwaysbuy.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_notalwaysbuy:
                ivNotalwaysbuy.setVisibility(View.INVISIBLE);
                ivAlwaysbuy.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void memberPriceSus(MemberPriceModel model) {
        disMissDialog();
        adapter.setList(model.getData());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

}
