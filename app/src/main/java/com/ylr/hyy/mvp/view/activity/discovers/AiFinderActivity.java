package com.ylr.hyy.mvp.view.activity.discovers;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.AiFinderAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.AiFinderContract;
import com.ylr.hyy.mvp.model.AiNewsModel;
import com.ylr.hyy.mvp.presenter.AiFinderPresenter;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

/**
 * AI助手
 */
public class AiFinderActivity extends BaseActivity<AiFinderContract.View, AiFinderContract.Presenter> implements AiFinderContract.View {
    @BindView(R.id.iv_aihelper_finder_return)
    ImageView ivAihelperFinderReturn;
    @BindView(R.id.tv_aihelper_use)
    TextView tvAihelperUse;
    @BindView(R.id.iv_aihelper_turnon)
    ImageView ivAihelperTurnon;
    @BindView(R.id.rv_ai_finder)
    RecyclerView rv;
    @BindView(R.id.iv_choosefriendorcustomer)
    ImageView ivChoosefriendorcustomer;


    private AiFinderAdapter adapter;

    //0朋友圈,1客户圈
    private int iscircle = 0;
    //当前第几页
    private int page = 1;
    //当前AI助手是否开启0关1开
    private int isOpen = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discovers_aihelper_finder;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected AiFinderContract.Presenter initPresenter() {
        mPresenter = new AiFinderPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        adapter = new AiFinderAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv.addItemDecoration(new RVSpace(SizeUtils.dp2px(6)));
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", page);
            jsonObject.put("size", 10);
            jsonObject.put("iscircle", 0);
            RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
            showDialog();
            mPresenter.upAiNews(body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_aihelper_finder_return, R.id.tv_aihelper_use, R.id.iv_aihelper_turnon,R.id.iv_choosefriendorcustomer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_aihelper_finder_return:
                finish();
                break;
            case R.id.tv_aihelper_use:
                break;
            case R.id.iv_aihelper_turnon:
                showDialog();
                mPresenter.switchAi(null);
                break;
            case R.id.iv_choosefriendorcustomer:
                break;
        }
    }

    @Override
    public void upAiNewsSus(AiNewsModel model) {
        disMissDialog();
        model.getData().getKeyword().add("添加");
        adapter.setData(model.getData().getKeyword());

        isOpen = model.getData().getIsshow();
        if (isOpen == 0) {
            GlideEngine.loadImage(ivAihelperTurnon, R.drawable.green_lock);
//            Glide.with(this).load(R.drawable.green_lock).into(ivAihelperTurnon);
        } else {
            GlideEngine.loadImage(ivAihelperTurnon, R.drawable.gray_lock);
//            Glide.with(this).load(R.drawable.gray_lock).into(ivAihelperTurnon);
        }
    }

    @Override
    public void switchAiSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
        if (isOpen == 0) {
            isOpen = 1;
            GlideEngine.loadImage(ivAihelperTurnon, R.drawable.green_lock);
//            Glide.with(this).load(R.drawable.green_lock).into(ivAihelperTurnon);
        } else {
            isOpen = 0;
            GlideEngine.loadImage(ivAihelperTurnon, R.drawable.gray_lock);

//            Glide.with(this).load(R.drawable.gray_lock).into(ivAihelperTurnon);
        }
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }


}


