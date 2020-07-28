package com.ylr.hyy.mvp.view.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.SPUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.mvp.contract.MainContract;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.presenter.MainPresenter;
import com.ylr.hyy.mvp.view.fragment.CustomerFragment;
import com.ylr.hyy.mvp.view.fragment.DiscoversFragment;
import com.ylr.hyy.mvp.view.fragment.FriendFragment;
import com.ylr.hyy.mvp.view.fragment.MeFragment;
import com.ylr.hyy.mvp.view.fragment.MessageFragment;
import com.ylr.hyy.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ylr.hyy.MVPApplication.HttpType;

public class MainActivity extends BaseActivity<MainContract.View,MainContract.Presenter> implements MainContract.View {
    @BindView(R.id.home_message)
    ImageView homeMessage;
    @BindView(R.id.home_friend)
    ImageView homeFriend;
    @BindView(R.id.home_customer)
    ImageView homeCustomer;
    @BindView(R.id.home_discovers)
    ImageView homeDiscovers;
    @BindView(R.id.home_me)
    ImageView homeMe;

    private List<Fragment> list;
    private List<ImageView> iv;
    private List<Integer> ivGray;
    private List<Integer> ivBlue;

    private FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected MainContract.Presenter initPresenter() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {
    }

    private static final String TAG = "MainActivity";
    @Override
    protected void initDatas() {
        list = new ArrayList<>();
        iv = new ArrayList<>();
        ivGray = new ArrayList<>();
        ivBlue = new ArrayList<>();

        MessageFragment messageFragment = new MessageFragment();
        list.add(messageFragment);
        list.add(new FriendFragment());
        list.add(new CustomerFragment());
        list.add(new DiscoversFragment());
        list.add(new MeFragment());

        iv.add(homeMessage);
        iv.add(homeFriend);
        iv.add(homeCustomer);
        iv.add(homeDiscovers);
        iv.add(homeMe);

        ivGray.add(R.drawable.messagary);
        ivGray.add(R.drawable.friendgray);
        ivGray.add(R.drawable.customergray);
        ivGray.add(R.drawable.discoversgray);
        ivGray.add(R.drawable.megray);

        ivBlue.add(R.drawable.msgblue);
        ivBlue.add(R.drawable.friendblue);
        ivBlue.add(R.drawable.customerblue);
        ivBlue.add(R.drawable.discoversblue);
        ivBlue.add(R.drawable.meblue);


        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.f1_content, messageFragment).show(messageFragment).commit();

        //登录获取是否实名认证 若是 另一台手机上登录 自动获取实名认证成功的信息
        if (getIntent().getIntExtra("isrealn",0) == 1) {
            RequestBody body = RequestBody.create(MediaType.parse(HttpType),"{}");
            mPresenter.isVoucher(body);
        }
    }

    public void ChangeFragmentPage(int pos) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (!list.get(pos).isAdded()) {
            transaction.add(R.id.f1_content, list.get(pos));
        }
        for (int i = 0; i < list.size(); i++) {
            if (pos == i) {
                transaction.show(list.get(i));
                iv.get(i).setBackgroundResource(ivBlue.get(i));
            } else {
                transaction.hide(list.get(i));
                iv.get(i).setBackgroundResource(ivGray.get(i));
            }
        }
        transaction.commit();
    }



    @OnClick({R.id.rl_home_message, R.id.rl_home_friend, R.id.rl_home_customer, R.id.rl_home_discovers, R.id.rl_home_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home_message:
                ChangeFragmentPage(0);
                break;
            case R.id.rl_home_friend:
                ChangeFragmentPage(1);
                break;
            case R.id.rl_home_customer:
                ChangeFragmentPage(2);
                break;
            case R.id.rl_home_discovers:
                ChangeFragmentPage(3);
                break;
            case R.id.rl_home_me:
                ChangeFragmentPage(4);
                break;
        }
    }

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtils.showToast("再按一次退出程序");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getIsVoucher(MeAuditSuccessModel model) {
        disMissDialog();
        SPUtils.getInstance().put("userName",model.getData().getName());
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
    }
}