package com.ylr.hyy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.MomentsModel;
import com.ylr.hyy.utils.NinthPalaceViewGroup;
import com.ylr.hyy.utils.RoundImageView;

import java.util.ArrayList;
import java.util.List;

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder> {
    private Context mContext;
    public MomentsAdapter(Context context){
        mContext = context;
        list  = new ArrayList<>();
    }

    private List<MomentsModel.DataBean> list;

    public void setList(List<MomentsModel.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<MomentsModel.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_moments,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(mContext).load(list.get(position).get)
        holder.ninthPalaceViewGroup.clearImg();

        if (!TextUtils.isEmpty(list.get(position).getImgs())) {
            String [] img = list.get(position).getImgs().split(",");
            holder.ninthPalaceViewGroup.addChild(img);
        }

        if (TextUtils.isEmpty(list.get(position).getVideos())) {
            holder.ivVideo.setVisibility(View.GONE);
        }else {
            holder.ivVideo.setVisibility(View.VISIBLE);
        }

        holder.tvComment.setText(list.get(position).getEvea().size()+"");
        holder.tvLike.setText(list.get(position).getFabulous().size()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        RoundImageView roundImageView;
        TextView tvName,tvTime,tvContent,tvComment,tvLike;
        ImageView ivVideo,ivComment,ivLike;
        NinthPalaceViewGroup ninthPalaceViewGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundImageView = itemView.findViewById(R.id.riv_item_moments_head);
            tvName = itemView.findViewById(R.id.tv_item_moments_name);
            tvTime = itemView.findViewById(R.id.tv_item_moments_time);
            tvContent = itemView.findViewById(R.id.tv_item_moments_content);
            tvComment = itemView.findViewById(R.id.tv_item_comments);
            tvLike = itemView.findViewById(R.id.tv_item_zan);
            ivVideo = itemView.findViewById(R.id.iv_moments_video);
            ivComment = itemView.findViewById(R.id.iv_item_comments);
            ivLike = itemView.findViewById(R.id.iv_item_zan);
            ninthPalaceViewGroup = itemView.findViewById(R.id.ninth_item);
        }
    }
}
