package com.ylr.hyy.mvp.view.activity.discovers;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.adapter.AiHelperAddKeywordAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.AiFinderContract;
import com.ylr.hyy.mvp.model.AiNewsModel;
import com.ylr.hyy.mvp.presenter.AiFinderPresenter;
import com.ylr.hyy.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;


/**
 * AI助手添加关键字
 */
public class AiHelperAddActivity extends BaseActivity<AiFinderContract.View, AiFinderContract.Presenter> implements AiFinderContract.View{
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_add_keyword_orderhelper)
    TextView tvAddKeywordOrderhelper;
    @BindView(R.id.et_add_keyword)
    EditText etAddKeyword;
    @BindView(R.id.iv_add_keyword_delete)
    ImageView ivAddKeywordDelete;
    @BindView(R.id.tv_add_keyword_sure)
    TextView tvAddKeywordSure;
    @BindView(R.id.tv_add_keyword_showkeyword)
    TextView tvAddKeywordShowkeyword;
    @BindView(R.id.rv_aihelper_add_showkeyword)
    RecyclerView rvAihelperAddShowkeyword;

    private AiHelperAddKeywordAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discovers_aihelper_keyword;
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
        adapter = new AiHelperAddKeywordAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvAihelperAddShowkeyword.setLayoutManager(manager);
        rvAihelperAddShowkeyword.setAdapter(adapter);
        upList();

    }

    //更新关键字列表
    private void upList(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page",1);
            jsonObject.put("size",1);
            jsonObject.put("iscircle",0);
            RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
            showDialog();
            mPresenter.upAiNews(body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_title_return, R.id.tv_add_keyword_orderhelper, R.id.iv_add_keyword_delete, R.id.tv_add_keyword_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.tv_add_keyword_orderhelper:
                break;
            case R.id.iv_add_keyword_delete:
                break;
            case R.id.tv_add_keyword_sure:
                if (TextUtils.isEmpty(etAddKeyword.getText().toString())) {
                    ToastUtils.showToast("请输入内容");
                    return;
                }
                showDialog();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("keyword",etAddKeyword.getText().toString());
                    jsonObject.put("type",1);
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.add(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private static final String TAG = "AiHelperAddActivity";
    @Override
    public void upAiNewsSus(AiNewsModel model) {
        disMissDialog();

        if (model.getData().getKeyword().size() != 0) {
            Log.i(TAG, "upAiNewsSus: ");
            adapter.setData(model.getData().getKeyword());
        }
    }

    @Override
    public void switchAiSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
