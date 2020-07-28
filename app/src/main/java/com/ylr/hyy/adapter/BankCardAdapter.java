package com.ylr.hyy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.BankCodeModel;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.utils.SwipeView;

import java.util.ArrayList;
import java.util.List;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder> {
    private Context mContext;

    public BankCardAdapter(Context context){
        mContext= context;
        list = new ArrayList<>();
    }
    private List<GetMyBankCardModel.DataBean> list;

    public void setList(List<GetMyBankCardModel.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bankcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBank.setText(list.get(position).getBank());
        holder.tvCode.setText(list.get(position).getAccountNo());
        holder.tvType.setText(list.get(position).getCardType());

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.deleteItem(list.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBank,tvType,tvCode,tvDelete;
        SwipeView swipeView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBank = itemView.findViewById(R.id.tv_addbankcard_bank);
            tvType = itemView.findViewById(R.id.tv_addbankcard_type);
            tvCode = itemView.findViewById(R.id.tv_addbankcard_cardcode);
            swipeView = itemView.findViewById(R.id.sview);
            tvDelete = itemView.findViewById(R.id.tv_item_bankcard_delete);

        }
    }

    public interface OnDeleteClickListener{
        void deleteItem(int id);
    }

    private OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
}
