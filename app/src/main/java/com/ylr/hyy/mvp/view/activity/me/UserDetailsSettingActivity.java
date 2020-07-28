package com.ylr.hyy.mvp.view.activity.me;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.utils.ItemViewGroup;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 好友/客户资料设置
 */
public class UserDetailsSettingActivity extends BaseActivity {

    @BindView(R.id.iv_title_return)
    ImageView ivTitleReturn;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.item_userdetails_setting1)
    ItemViewGroup itemUserdetailsSetting1;
    @BindView(R.id.item_userdetails_setting2)
    ItemViewGroup itemUserdetailsSetting2;
    @BindView(R.id.fl_userdetails_setting_blacklist)
    FrameLayout flUserdetailsSettingBlacklist;
    @BindView(R.id.tv_userdetails_delete)
    TextView tvDelete;
    @BindView(R.id.iv_userdetails_button)
    ImageView ivButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userdetailssetting;
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
        tvTitleName.setText("用户资料设置");
        itemUserdetailsSetting1.setContent(0,"朋友权限","",true);
        itemUserdetailsSetting2.setContent(0,"投诉","",true);
    }


    @OnClick({R.id.iv_title_return, R.id.iv_userdetails_button,R.id.item_userdetails_setting1,R.id.tv_userdetails_delete, R.id.item_userdetails_setting2, R.id.fl_userdetails_setting_blacklist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_return:
                finish();
                break;
            case R.id.item_userdetails_setting1:
                break;
            case R.id.item_userdetails_setting2:
                break;
            case R.id.fl_userdetails_setting_blacklist:
                break;
            case R.id.tv_userdetails_delete:
                break;
            case R.id.iv_userdetails_button:
                break;
        }
    }
}
