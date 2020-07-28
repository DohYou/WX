package com.ylr.hyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupTagAdapter extends RecyclerView.Adapter<CreateGroupTagAdapter.ViewHolder> {
    private Context mContext;
    public CreateGroupTagAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tag,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTag.setText(list.get(position));
        holder.tvTag.setOnClickListener(v -> {
            if (onChangeLogListener != null) {
                ToastUtils.showToast(list.get(position));
                onChangeLogListener.change(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_tag);
        }
    }

    public interface OnChangeLogListener{
        void change(int pos);
    }

    private OnChangeLogListener onChangeLogListener;

    public void setOnChangeLogListener(OnChangeLogListener onChangeLogListener) {
        this.onChangeLogListener = onChangeLogListener;
    }
}