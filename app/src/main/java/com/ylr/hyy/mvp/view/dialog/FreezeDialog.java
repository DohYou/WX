package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseDialogX;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 冻结账号
 */
public class FreezeDialog extends BaseDialogX {
    @BindView(R.id.tv_dialog_freeze_msg)
    TextView tvDialogFreezeMsg;
    @BindView(R.id.tv_dialog_freeze_password)
    TextView tvDialogFreezePassword;
    @BindView(R.id.tv_dialog_freeze_help)
    TextView tvDialogFreezeHelp;
    @BindView(R.id.tv_dialog_freeze_cancel)
    TextView tvDialogfreezeCancel;

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_freeze;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void config(Dialog dialog) {
        setCancelable(false);
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

    @OnClick({R.id.tv_dialog_freeze_msg, R.id.tv_dialog_freeze_password, R.id.tv_dialog_freeze_help, R.id.tv_dialog_freeze_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dialog_freeze_msg:
                if (onFreezeListener != null) {
                    onFreezeListener.msgFreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_freeze_password:
                if (onFreezeListener != null) {
                    onFreezeListener.passwordFreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_freeze_help:
                if (onFreezeListener != null) {
                    onFreezeListener.helpFreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_freeze_cancel:
                this.dismiss();
                break;
        }
    }


    public interface OnFreezeListener {
        void msgFreeze();

        void passwordFreeze();

        void helpFreeze();
    }

    private OnFreezeListener onFreezeListener;

    public void setOnFreezeListener(OnFreezeListener onFreezeListener) {
        this.onFreezeListener = onFreezeListener;
    }
}
