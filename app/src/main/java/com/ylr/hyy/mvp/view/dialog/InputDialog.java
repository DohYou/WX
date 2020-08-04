package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.TUIKit;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseDialogX;

import butterknife.BindView;

public class InputDialog extends BaseDialogX {
    @BindView(R.id.et_dialog_content)
    EditText etDialogContent;
    @BindView(R.id.tv_dialog_input_commit)
    TextView tvDialogInputCommit;
    private static final String TAG = "InputDialog";

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_input;
    }

    private String dialogHint = "回复：";

    public void setDialogHint(String dialogHint) {
        this.dialogHint = dialogHint;
    }

    public void clearText(){
        etDialogContent.setText("");
    }

    @Override
    protected void initView(View view) {
        Log.i(TAG, "initView: ");
        etDialogContent.setHint(dialogHint);
        etDialogContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etDialogContent.getText().toString())) {
                    tvDialogInputCommit.setBackgroundResource(R.drawable.re_solid_r5_bule);
                }else {
                    tvDialogInputCommit.setBackgroundResource(R.drawable.re_solid_r5_gray);
                }
            }
        });

        tvDialogInputCommit.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(etDialogContent.getText().toString())) {
                dismiss();
                onCommentListener.comment(etDialogContent.getText().toString().trim());
            }
        });
    }

    @Override
    protected void config(Dialog dialog) {
        Log.i(TAG, "config: ");

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
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }

    public interface OnCommentListener{
        void comment(String content);
    }

    private OnCommentListener onCommentListener;

    public void setOnCommentListener(OnCommentListener onCommentListener) {
        this.onCommentListener = onCommentListener;
    }
}
