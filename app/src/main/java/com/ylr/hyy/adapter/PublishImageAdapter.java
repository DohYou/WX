package com.ylr.hyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.ylr.hyy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择上传图片
 */
public class PublishImageAdapter extends RecyclerView.Adapter<PublishImageAdapter.ViewHolder> {
    private Context mContext;
    public PublishImageAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<String> getList() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).equals("添加")) {
            holder.ivDelete.setVisibility(View.GONE);
            GlideEngine.loadImage(holder.imageView,R.drawable.add_pics);
        }else {
            holder.ivDelete.setVisibility(View.VISIBLE);
            GlideEngine.loadImage(holder.imageView,list.get(position));
        }
        holder.imageView.setOnClickListener(v -> {
            if (list.get(position).equals("添加")) {
                if (onUpListListener != null) {
                    onUpListListener.start();
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.get(list.size() - 1).equals("添加")) {
                    list.add("添加");
                }
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() > 9 ? 9: list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item_image);
            ivDelete = itemView.findViewById(R.id.iv_item_delete);
        }
    }

    public interface OnUpListListener{
        void start();
    }

    private OnUpListListener onUpListListener;

    public void setOnUpListListener(OnUpListListener onUpListListener) {
        this.onUpListListener = onUpListListener;
    }
}
