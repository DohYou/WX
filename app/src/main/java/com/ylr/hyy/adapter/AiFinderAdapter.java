package com.ylr.hyy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.mvp.view.activity.discovers.AiHelperAddActivity;

import java.util.ArrayList;
import java.util.List;

public class AiFinderAdapter extends RecyclerView.Adapter<AiFinderAdapter.ViewHolder> {
    private Context mContext;
    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public AiFinderAdapter(Context context){
        mContext = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_aihelper_finder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (data.get(position).equals("添加")) {
            holder.iv.setVisibility(View.VISIBLE);
            holder.tv.setVisibility(View.GONE);
        }else {
            holder.tv.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.GONE);
        }
        holder.tv.setText(data.get(position));
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, AiHelperAddActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_finder_keyword);
            iv = itemView.findViewById(R.id.iv_item_is_add);
        }
    }
}
