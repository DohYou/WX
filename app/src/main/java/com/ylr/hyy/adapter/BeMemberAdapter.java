package com.ylr.hyy.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.MemberPriceModel;

import java.util.ArrayList;
import java.util.List;

public class BeMemberAdapter extends RecyclerView.Adapter<BeMemberAdapter.ViewHolder> {
    private Context mContext;

    public BeMemberAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<MemberPriceModel.DataBean> list;

    public void setList(List<MemberPriceModel.DataBean>list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<MemberPriceModel.DataBean>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_be_member_green,parent,false);
        return new ViewHolder(view);
    }


    private int pos = -1;//点击的ITEM
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position == pos) {
            holder.rl.setBackgroundResource(R.drawable.re_solid_be_member_purple);
        }else {
            holder.rl.setBackgroundResource(R.drawable.re_solid_be_member_white);
        }

        if (list.get(position).getDiscount() == 0) {
            holder.imageView.setVisibility(View.INVISIBLE);
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
        }

      holder.tvMoney.setText(list.get(position).getRealprice()+"");
      holder.tvTime.setText(list.get(position).getTitle());

        SpannableStringBuilder builder = new SpannableStringBuilder("￥"+list.get(position).getRealprice());
        builder.setSpan(new AbsoluteSizeSpan(SizeUtils.sp2px(15)),0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tvMoney.setText(builder);


        holder.rl.setOnClickListener(v -> {
            if (onSelectListener != null) {
                onSelectListener.select(list.get(position).getId());
                pos = position;
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime,tvMoney;
        ImageView imageView;
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_ninegreen);
            rl = itemView.findViewById(R.id.rl_be_member_bg);
            tvTime = itemView.findViewById(R.id.tv_be_member_time);
            tvMoney = itemView.findViewById(R.id.tv_be_member_money);
        }
    }

    public interface OnSelectListener{
        void select(int id);
    }

    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }
}
