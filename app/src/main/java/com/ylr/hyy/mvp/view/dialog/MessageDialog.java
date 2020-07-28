package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseDialogX;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageDialog extends BaseDialogX {
    @BindView(R.id.iv_add_qtalk)
    ImageView ivAddQtalk;
    @BindView(R.id.rl_add_qtalk)
    RelativeLayout rlAddQtalk;
    @BindView(R.id.iv_add_add)
    ImageView ivAddAdd;
    @BindView(R.id.rl_add_add)
    RelativeLayout rlAddAdd;
    @BindView(R.id.iv_add_scan)
    ImageView ivAddScan;
    @BindView(R.id.rl_add_scan)
    RelativeLayout rlAddScan;

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_message;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void config(Dialog dialog) {setCancelable(false);
    }

    @Override
    protected void initOnStart() {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window != null)
            window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0.5f;
        window.setAttributes(lp);
    }

    @OnClick({R.id.rl_add_qtalk, R.id.rl_add_add, R.id.rl_add_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_add_qtalk:
                break;
            case R.id.rl_add_add:
                break;
            case R.id.rl_add_scan:
                break;
        }
    }
}
