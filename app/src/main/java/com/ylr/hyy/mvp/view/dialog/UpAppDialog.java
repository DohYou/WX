package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ylr.hyy.R;


public class UpAppDialog extends DialogFragment implements View.OnClickListener {
    private ImageView ivCancel;
    private TextView tvDown,tvUpContent;

    private String upContent;

    public void setUpContent(String upContent) {
        this.upContent = upContent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_up_app,container,false);
        ivCancel = view.findViewById(R.id.iv_file_cancel);
        tvDown = view.findViewById(R.id.tv_dialog_down);
        tvUpContent = view.findViewById(R.id.tv_up_content_dialog);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivCancel.setOnClickListener(this);
        tvDown.setOnClickListener(this);
        tvUpContent.setText(upContent);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //设置dialog的样式
        Dialog dialog = new Dialog(getActivity(),R.style.DialogFragment) {
            @Override
            public void onBackPressed() {
                super.onBackPressed();
            }

        };
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.dimAmount = 0.5f;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_file_cancel:
                dismiss();
                break;
            case R.id.tv_dialog_down:
                if (listener != null) {
                    listener.down();
                }
                break;
        }
    }

    public interface OnDownListener{
        void down();
    }

    private OnDownListener listener;

    public void setListener(OnDownListener listener) {
        this.listener = listener;
    }
}
