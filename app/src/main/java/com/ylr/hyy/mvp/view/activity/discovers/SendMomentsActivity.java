package com.ylr.hyy.mvp.view.activity.discovers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.adapter.PublishImageAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.view.activity.me.HeadImageActivity;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ZhiHuGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发朋友圈 发动态
 */
public class SendMomentsActivity extends BaseActivity {
    @BindView(R.id.rv_sendmoments)
    RecyclerView recyclerView;


    private PublishImageAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sendmoments;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        list = new ArrayList<>();
        list.add("添加");
    }

    @Override
    protected void initDatas() {
        adapter = new PublishImageAdapter(this);
        recyclerView.addItemDecoration(new RVSpace(10));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);
        adapter.setList(list);

        adapter.setOnUpListListener(() ->
                Matisse
                        .from(SendMomentsActivity.this)
                        //选择图片
                        .choose(MimeType.ofImage())
                        //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                        .showSingleMediaType(true)
                        //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true,"com.thunder.sample.fileprovider"))
                        //最大选择数量
                        .maxSelectable(size - list.size())
                        //选择方向
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        //界面中缩略图的质量
                        .thumbnailScale(0.8f)
                        //黑色主题
                        .theme(R.style.Matisse_Dracula)
                        //Glide加载方式
                        .imageEngine(new ZhiHuGlideEngine())
                        //请求码
                        .forResult(1)
        );

    }

    private int size = 10;//当前能选择几张图片
    private List<String> list;//当前rv里面有多少数据
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1){
                //图片路径 同样视频地址也是这个 根据requestCode
                List<String> pathList = Matisse.obtainPathResult(data);
                for (int i = 0; i < pathList.size(); i++) {
                    if (list.get(list.size() - 1).equals("添加")) {
                        list.remove(list.size() - 1);
                    }
                    list.add(pathList.get(i));
                    if (list.size() != 9) {
                        list.add("添加");
                    }
                    adapter.setList(list);
                }
            }
        }
    }
}
