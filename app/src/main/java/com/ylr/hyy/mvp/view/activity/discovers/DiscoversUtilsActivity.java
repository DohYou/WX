package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发现实用小工具
 */
public class DiscoversUtilsActivity extends BaseActivity {
    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.itm_discover_utils1)
    ItemViewGroup itmDiscoverUtils1;
    @BindView(R.id.itm_discover_utils2)
    ItemViewGroup itmDiscoverUtils2;
    @BindView(R.id.itm_discover_utils3)
    ItemViewGroup itmDiscoverUtils3;
    @BindView(R.id.itm_discover_utils4)
    ItemViewGroup itmDiscoverUtils4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discovers_utils;
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
        itmDiscoverUtils1.setContent(R.drawable.gray_airplane,"群发助手","",true);
        itmDiscoverUtils2.setContent(R.drawable.txtgray,"图片转文字","",true);
        itmDiscoverUtils3.setContent(R.drawable.notegray,"备忘录","",true);
        itmDiscoverUtils4.setContent(R.drawable.cleangray,"一键清粉","",true);


        tvTitleName.setText("实用小工具");
    }

    private static final String TAG = "DiscoversUtilsActivity";
    @OnClick({R.id.iv_title_return, R.id.itm_discover_utils1, R.id.itm_discover_utils2, R.id.itm_discover_utils3,R.id.itm_discover_utils4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.itm_discover_utils1://群发助手
                startActivity(new Intent(this,PeopleSendHelperActivity.class));
                Log.i(TAG, "onViewClicked: ");
                break;
            case R.id.itm_discover_utils2://图片转文字
                break;
            case R.id.itm_discover_utils3://备忘录
                break;
            case R.id.itm_discover_utils4://一键清粉
                startActivity(new Intent(this,CleanFansActivity.class));
                break;
        }
    }
}
