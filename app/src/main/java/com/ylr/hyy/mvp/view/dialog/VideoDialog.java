package com.ylr.hyy.mvp.view.dialog;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseDialogX;

import butterknife.BindView;
import retrofit2.http.GET;

public class VideoDialog extends BaseDialogX {
    @BindView(R.id.dialog_video)
    VideoView dialogVideo;
    @BindView(R.id.tv_dialog_close)
    TextView tvDialogClose;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private static final String TAG = "VideoDialog";
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected int setStyle() {
        return R.style.DialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_video;
    }

    @Override
    protected void initView(View view) {
        tvShow.setVisibility(View.VISIBLE);
        dialogVideo.setVideoPath(url);
        dialogVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                tvShow.setVisibility(View.GONE);
                dialogVideo.start();
            }
        });
        tvDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                dialogVideo.stopPlayback();//停止播放视频,并且释放
                dialogVideo.suspend();//在任何状态下释放媒体播放器
            }
        });
    }

    @Override
    protected void config(Dialog dialog) {

    }

    @Override
    protected void initOnStart() {

    }
}
