package com.ylr.hyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;

public class QChatDetailsAdapter extends RecyclerView.Adapter<QChatDetailsAdapter.ViewHolder> {

    private Context mContext;
    public QChatDetailsAdapter(Context context){
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qchatdetailspeople,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.item_qchatdetailspeople);
        }
    }
}
