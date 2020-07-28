package com.ylr.hyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.CreateGroupModel;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupSortAdapter extends RecyclerView.Adapter<CreateGroupSortAdapter.ViewHolder> {
    private Context mContext;
    public CreateGroupSortAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<CreateGroupModel.DataBean.LabelListBean> list;

    public void setList(List<CreateGroupModel.DataBean.LabelListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_create_group_sort,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getLablename());
        holder.tv.setOnClickListener(v -> {
            if (onItemListener != null) {
                onItemListener.itemClick(list.get(position).getLableid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_create_sort_name);
        }
    }

    public interface OnItemListener{
        void itemClick(String id);
    }

    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}