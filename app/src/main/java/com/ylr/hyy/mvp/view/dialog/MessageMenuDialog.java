package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.SizeUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseDialogX;
import com.ylr.hyy.mvp.view.activity.message.AddFriendActivity;
import com.ylr.hyy.mvp.view.activity.message.CreatePeopleTalkActivity;

import butterknife.OnClick;

public class MessageMenuDialog extends BaseDialogX {
    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_message_menu;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void config(Dialog dialog) {

    }

    @Override
    protected void initOnStart() {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window != null)
            window.setGravity(Gravity.RIGHT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.height;
        lp.height = lp.width;
        lp.x = SizeUtils.dp2px(16);
        lp.y = SizeUtils.dp2px(44);
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }

    @OnClick({R.id.fl_message_group, R.id.fl_message_add, R.id.fl_message_sao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_message_group:
                dismiss();
                startActivity(new Intent(getActivity(), CreatePeopleTalkActivity.class));
                break;
            case R.id.fl_message_add:
                dismiss();
                startActivity(new Intent(getActivity(), AddFriendActivity.class));
                break;
            case R.id.fl_message_sao:
                dismiss();
                onGetCodeListener.startSao();
                break;
        }
    }

    public interface OnGetCodeListener{
        void startSao();
    }

    private OnGetCodeListener onGetCodeListener;

    public void setOnGetCodeListener(OnGetCodeListener onGetCodeListener) {
        this.onGetCodeListener = onGetCodeListener;
    }
}
