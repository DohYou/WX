package com.ylr.hyy.mvp.view.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.qcloud.tim.uikit.component.gatherimage.DynamicLayoutView;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.QChatDetailsAdapter;
import com.ylr.hyy.base.Base;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.DelAndLeaveContract;
import com.ylr.hyy.mvp.presenter.DelAndLeavePresenter;
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

public class QChatDetailsActivity extends BaseActivity<DelAndLeaveContract.View,DelAndLeaveContract.Presenter> implements DelAndLeaveContract.View {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.rv_qchatdetails)
    RecyclerView rvQchatdetails;
    @BindView(R.id.iv_qchatdetailsname)
    ImageView ivQchatdetailsname;
    @BindView(R.id.iv_qchatdetails_myname)
    ImageView ivQchatdetailsMyname;
    @BindView(R.id.iv_qchatdetails_qtwo)
    ImageView ivQchatdetailsQtwo;
    @BindView(R.id.iv_qchatdetails_off1)
    ImageView ivQchatdetailsOff1;
    @BindView(R.id.iv_qchatdetails_on1)
    ImageView ivQchatdetailsOn1;
    @BindView(R.id.iv_qchatdetails_off2)
    ImageView ivQchatdetailsOff2;
    @BindView(R.id.iv_qchatdetails_on2)
    ImageView ivQchatdetailsOn2;
    @BindView(R.id.rl_qchatdetails_1)
    RelativeLayout rlQchatdetails1;
    @BindView(R.id.rl_qchatdetails_2)
    RelativeLayout rlQchatdetails2;
    @BindView(R.id.rl_qchatdetails_3)
    RelativeLayout rlQchatdetails3;
    @BindView(R.id.rl_qchatdetails_4)
    RelativeLayout rlQchatdetails4;
    @BindView(R.id.rl_qchatdetails_5)
    RelativeLayout rlQchatdetails5;
    @BindView(R.id.rl_qchatdetails_6)
    RelativeLayout rlQchatdetails6;
    @BindView(R.id.rl_qchatdetails_7)
    RelativeLayout rlQchatdetails7;
    @BindView(R.id.rl_qchatdetails_8)
    RelativeLayout rlQChatdetails8;
    @BindView(R.id.iv_qchatdetails_off3)
    ImageView ivQchatdetailsOff3;
    @BindView(R.id.iv_qchatdetails_on3)
    ImageView ivQchatdetailsOn3;
    @BindView(R.id.rl_qchatdetails_9)
    RelativeLayout rlQchatdetails9;
    @BindView(R.id.rl_qchatdetails_10)
    RelativeLayout rlQchatdetails10;
    @BindView(R.id.tv_deleteandleave)
    TextView tvDeleteandleave;

    private RecyclerView recyclerView;
    private QChatDetailsAdapter adapter;

    private String groupId;//群聊ID

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qchatdetails;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected DelAndLeaveContract.Presenter initPresenter() {
        mPresenter = new DelAndLeavePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        tvTitleName.setText("群聊信息(4)");

        adapter = new QChatDetailsAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerView.addItemDecoration(new RVSpace(2, 2, 2, 2));
        recyclerView.setAdapter(adapter);

        groupId = getIntent().getStringExtra("id");//拿到id


    }
    private RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.delAndLeave(body);
    }

    private JSONObject jsonObject;
    @OnClick({R.id.iv_title_return, R.id.iv_qchatdetails_off1, R.id.iv_qchatdetails_on1, R.id.iv_qchatdetails_off2, R.id.iv_qchatdetails_on2, R.id.rl_qchatdetails_1, R.id.rl_qchatdetails_2, R.id.rl_qchatdetails_3, R.id.rl_qchatdetails_4, R.id.rl_qchatdetails_5, R.id.rl_qchatdetails_6, R.id.rl_qchatdetails_7
    ,R.id.iv_qchatdetails_off3, R.id.iv_qchatdetails_on3, R.id.rl_qchatdetails_8, R.id.rl_qchatdetails_9, R.id.rl_qchatdetails_10, R.id.tv_deleteandleave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.iv_qchatdetails_off1://置顶聊天
                ivQchatdetailsOff1.setVisibility(View.INVISIBLE);
                ivQchatdetailsOn1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qchatdetails_on1:
                ivQchatdetailsOn1.setVisibility(View.INVISIBLE);
                ivQchatdetailsOff1.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qchatdetails_off2://消息免打扰
                ivQchatdetailsOff2.setVisibility(View.INVISIBLE);
                ivQchatdetailsOn2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qchatdetails_on2:
                ivQchatdetailsOn2.setVisibility(View.INVISIBLE);
                ivQchatdetailsOff2.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qchatdetails_off3://截屏通知
                ivQchatdetailsOff3.setVisibility(View.INVISIBLE);
                ivQchatdetailsOn3.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_qchatdetails_on3:
                ivQchatdetailsOn3.setVisibility(View.INVISIBLE);
                ivQchatdetailsOff3.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_qchatdetails_1://群聊名称
                break;
            case R.id.rl_qchatdetails_2://我在本群的昵称
                break;
            case R.id.rl_qchatdetails_3://群二维码
                break;
            case R.id.rl_qchatdetails_4://群公告
                break;
            case R.id.rl_qchatdetails_5://群管理
                startActivity(new Intent(this, QAdminActivity.class));
                break;
            case R.id.rl_qchatdetails_6://查看收发的视频/图片
                break;
            case R.id.rl_qchatdetails_7://查找聊天内容
                break;
            case R.id.rl_qchatdetails_8://安全加密
                break;
            case R.id.rl_qchatdetails_9://清除聊天记录
                break;
            case R.id.rl_qchatdetails_10://投诉
                break;
            case R.id.tv_deleteandleave://删除并退出
                try {
                    jsonObject.put("GroupId",groupId);
                    RequestBody body = RequestBody.create(MediaType.parse(HttpType),jsonObject.toString());
                    showDialog();
                    mPresenter.delAndLeave(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void delAndLeaveSus(Base base) {
        disMissDialog();
        ToastUtils.showToast(base.getMsg());

    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }
}
