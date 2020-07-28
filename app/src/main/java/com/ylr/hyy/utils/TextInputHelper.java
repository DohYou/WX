package com.ylr.hyy.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**https://www.jianshu.com/p/fd3795e8a6b3
 * 文本输入辅助类，通过管理多个TextView或者EditText输入是否为空来启用或者禁用按钮的点击事件
 */
public class TextInputHelper implements TextWatcher {

    private View mMainView;//操作按钮的View
    private List<TextView> mViewSet;//TextView集合，子类也可以（EditText、TextView、Button）
    private boolean isAlpha;//是否设置透明度

    public TextInputHelper(View view) {
        this(view, true);
    }

    /**
     * 构造函数
     *
     * @param view              跟随EditText或者TextView输入为空来判断启动或者禁用这个View
     * @param alpha             是否需要设置透明度
     */
    public TextInputHelper(View view, boolean alpha) {
        if (view == null) throw new IllegalArgumentException("The view is empty");
        mMainView = view;
        isAlpha = alpha;
    }

    /**
     * 添加EditText或者TextView监听
     *
     * @param views     传入单个或者多个EditText或者TextView对象
     */
    public void addViews(TextView... views) {
        if (views == null) return;

        if (mViewSet == null) {
            mViewSet = new ArrayList<>(views.length - 1);
        }

        for (TextView view : views) {
            view.addTextChangedListener(this);
            mViewSet.add(view);
        }
        afterTextChanged(null);
    }

    /**
     * 移除EditText监听，避免内存泄露
     */
    public void removeViews() {
        if (mViewSet == null) return;

        for (TextView view : mViewSet) {
            view.removeTextChangedListener(this);
        }
        mViewSet.clear();
        mViewSet = null;
    }

    // TextWatcher

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public synchronized void afterTextChanged(Editable s) {
        if (mViewSet == null) return;

        for (TextView view : mViewSet) {
            if ("".equals(view.getText().toString())) {
                setEnabled(false);
                return;
            }
        }

        setEnabled(true);
    }

    /**
     * 设置View的事件
     *
     * @param enabled               启用或者禁用View的事件
     */
    public void setEnabled(boolean enabled) {
        if (enabled == mMainView.isEnabled()) return;

        if (enabled) {
            //启用View的事件
            mMainView.setEnabled(true);
            if (isAlpha) {
                //设置不透明
                mMainView.setAlpha(1f);
            }
        }else {
            //禁用View的事件
            mMainView.setEnabled(false);
            if (isAlpha) {
                //设置半透明
                mMainView.setAlpha(0.5f);
            }
        }
    }
}