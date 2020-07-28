package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.SearchFriendContract;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.mvp.model.SearchFriendModel;
import com.ylr.hyy.mvp.presenter.SearchFriendPresenter;
import com.ylr.hyy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class SearchFriendActivity extends BaseActivity<SearchFriendContract.View, SearchFriendContract.Presenter> implements SearchFriendContract.View {
    @BindView(R.id.et_search_friend_content)
    EditText etSearchFriendContent;
    @BindView(R.id.iv_search_friend_head)
    ImageView ivSearchFriendHead;
    @BindView(R.id.tv_search_friend_show)
    TextView tvSearchFriendShow;
    @BindView(R.id.fl_search_friend_show)
    FrameLayout flSearchFriendShow;
    @BindView(R.id.tv_search_no_friend)
    TextView tvSearchNoFriend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_friend;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected SearchFriendContract.Presenter initPresenter() {
        mPresenter = new SearchFriendPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        etSearchFriendContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etSearchFriendContent.getText().toString())) {
                    flSearchFriendShow.setVisibility(View.GONE);
                    tvSearchNoFriend.setVisibility(View.GONE);
                }else {
                    flSearchFriendShow.setVisibility(View.VISIBLE);
                    ivSearchFriendHead.setBackgroundResource(R.drawable.add_friendsearch);
                    SpannableStringBuilder builder = new SpannableStringBuilder("搜索："+etSearchFriendContent.getText().toString());
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#237EFE")),3,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvSearchFriendShow.setText(builder);
                }
            }
        });
    }

    private int friendId = -1;
    @Override
    public void searchFriendSus(SearchFriendModel model) {
        disMissDialog();
        if (model != null && model.getData() != null && !TextUtils.isEmpty(model.getData().getHeadimgurl())) {
            friendId = model.getData().getId();
            Glide.with(this).load(model.getData().getHeadimgurl()).into(ivSearchFriendHead);
            tvSearchFriendShow.setText(model.getData().getNickname());
        }else {
            flSearchFriendShow.setVisibility(View.GONE);
            tvSearchNoFriend.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getFriendNewsSus(FriendModel model) {
        disMissDialog();
        //1好友 0不是好友
        Intent intent = new Intent();
        intent.putExtra("model",new Gson().toJson(model));
        if (model.getData().getWhetherFriend() == 0) {
            intent.setClass(this,NoFriendActivity.class);
        }else {
            intent.setClass(this,FriendNewsActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    @OnClick({R.id.tv_search_friend_cancel, R.id.tv_search_friend_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_friend_cancel:
                finish();
                break;
            case R.id.tv_search_friend_show:
                if (tvSearchFriendShow.getText().toString().startsWith("搜索：")) {
                    showDialog();
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("search",etSearchFriendContent.getText().toString());
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    mPresenter.searchFriend(body);
                }else {
                    if (friendId != -1) {
                        showDialog();
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("friendid",friendId);
                        RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                        mPresenter.getFriendNews(body);
                    }
                }
                break;
        }
    }
}
