package com.ylr.hyy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.CreateGroupModel;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupSelectAdapter extends RecyclerView.Adapter<CreateGroupSelectAdapter.ViewHolder> {
    private Context mContext;
    public CreateGroupSelectAdapter(Context context){
        mContext = context;
        head = new ArrayList<>();
        integers = new ArrayList<>();
    }

    private List<String> head;
    private List<Integer> integers;

    public void addList(String head,int id) {
        boolean isHaveId = false;
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) == id) {
                this.head.remove(i);
                this.integers.remove(i);
                isHaveId = true;
                break;
            }
        }
        if (!isHaveId) {
            this.head.add(head);
            this.integers.add(id);
        }
        notifyDataSetChanged();
    }

    public void cleanList(){
        head = new ArrayList<>();
        integers = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_create_group_select,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(head.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return head.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item_create_select);
        }
    }
}

