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

import com.bumptech.glide.Glide;
import com.tencent.imsdk.v2.V2TIMFriendApplication;
import com.tencent.imsdk.v2.V2TIMFriendApplicationResult;
import com.ylr.hyy.R;

import java.util.ArrayList;
import java.util.List;

public class VerifyMsgAdapter extends RecyclerView.Adapter<VerifyMsgAdapter.ViewHolder>{

    private Context mContext;
    public VerifyMsgAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<V2TIMFriendApplication> list;

    public void setList(List<V2TIMFriendApplication> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_verifymsg,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(list.get(position).getFaceUrl()).into(holder.imageView);
        holder.textView.setText(list.get(position).getNickname());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAgreeListener.agree(list.get(position).getAddSource(),list.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView,tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_verifymsg_agree);
            textView = itemView.findViewById(R.id.tv_verifymsg_name);
            imageView = itemView.findViewById(R.id.iv_verifymsg_header);
        }
    }

    public interface OnAgreeListener{
        void agree(String type,V2TIMFriendApplication data,int pos);
    }

    private OnAgreeListener onAgreeListener;

    public void setOnAgreeListener(OnAgreeListener onAgreeListener) {
        this.onAgreeListener = onAgreeListener;
    }

    public void removeView(int pos){
        list.remove(pos);
        notifyDataSetChanged();
    }
}