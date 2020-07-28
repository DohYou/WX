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

public class UnfreezeDialog extends BaseDialogX {
    @BindView(R.id.tv_dialog_unfreeze_msg)
    TextView tvDialogUnfreezeMsg;
    @BindView(R.id.tv_dialog_unfreeze_password)
    TextView tvDialogUnfreezePassword;
    @BindView(R.id.tv_dialog_unfreeze_help)
    TextView tvDialogUnfreezeHelp;
    @BindView(R.id.tv_dialog_unfreeze_cancel)
    TextView tvDialogUnfreezeCancel;

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_unfreeze;
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

    @OnClick({R.id.tv_dialog_unfreeze_msg, R.id.tv_dialog_unfreeze_password, R.id.tv_dialog_unfreeze_help, R.id.tv_dialog_unfreeze_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dialog_unfreeze_msg:
                if (onUnfreezeListener!= null) {
                    onUnfreezeListener.msgUnfreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_unfreeze_password:
                if (onUnfreezeListener!=null) {
                    onUnfreezeListener.passwordUnfreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_unfreeze_help:
                if (onUnfreezeListener!=null) {
                    onUnfreezeListener.helpUnfreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_unfreeze_cancel:
                this.dismiss();
                break;

        }
    }

    public interface OnUnfreezeListener{
        void msgUnfreeze();
        void passwordUnfreeze();
        void helpUnfreeze();
    }

    private OnUnfreezeListener onUnfreezeListener;

    public void setOnUnfreezeListener(OnUnfreezeListener onUnfreezeListener){
        this.onUnfreezeListener = onUnfreezeListener;
    }
}

