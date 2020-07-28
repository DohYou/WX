package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.text.TextUtils;
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
 * 选择冻结类型
 */
public class ChooseFreezeDialog extends BaseDialogX {
    @BindView(R.id.tv_dialog_choose_freeze)
    TextView tvDialogChooseFreeze;
    @BindView(R.id.tv_dialog_choose_unfreeze)
    TextView tvDialogChooseUnfreeze;
    private String text1;
    private String text2;

    public void setText(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }


    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_choose_freeze;
    }

    @Override
    protected void initView(View view) {
        if (!TextUtils.isEmpty(text1)) {
            tvDialogChooseFreeze.setText(text1);
            tvDialogChooseUnfreeze.setText(text2);
        }
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


    @OnClick({R.id.tv_dialog_choose_freeze, R.id.tv_dialog_choose_unfreeze, R.id.tv_dialog_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dialog_choose_freeze:
                if (onChooseListener != null) {
                    onChooseListener.freeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_choose_unfreeze:
                if (onChooseListener != null) {
                    onChooseListener.unfreeze();
                    this.dismiss();
                }
                break;
            case R.id.tv_dialog_cancel:
                this.dismiss();
                break;
        }
    }

    public interface OnChooseListener {
        void freeze();

        void unfreeze();
    }

    private OnChooseListener onChooseListener;

    public void setOnChooseListener(OnChooseListener onChooseListener) {
        this.onChooseListener = onChooseListener;
    }
}
