package com.ylr.hyy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.utils.create_group.BaseHolder;
import com.ylr.hyy.utils.create_group.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupListAdapter extends BaseRecyclerAdapter<CreateGroupModel.DataBean.AndroidListBean, BaseHolder> {


    public CreateGroupListAdapter(Context context) {
        super(context);
    }
    public CreateGroupListAdapter(Context context,List<CreateGroupModel.DataBean.AndroidListBean> dataList) {
        super(context,dataList);
    }

    @Override
    public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(parent, R.layout.item_create_group_list);
    }

    private static final String TAG = "CreateGroupListAdapter";
    @Override
    public void bindCustomViewHolder(BaseHolder holder, int position) {
        CreateGroupModel.DataBean.AndroidListBean bean = getItem(position);

        if (bean.isSelect()) {
            Glide.with(getmContext()).load(R.drawable.addsuccess).into((ImageView)holder.getView(R.id.iv_item_create_group_list));
        }else {
            Glide.with(getmContext()).load(R.drawable.topup_no_check).into((ImageView)holder.getView(R.id.iv_item_create_group_list));
        }


        Log.i(TAG, "bindCustomViewHolder: "+new Gson().toJson(bean));
        ((TextView)holder.getView(R.id.iv_item_create_group_list_head_name)).setText(bean.getNickname());
        Glide.with(getmContext()).load(bean.getHandimg()).into((ImageView)holder.getView(R.id.iv_item_create_group_list_head));
        if (position==0){
            holder.getView(R.id.header).setVisibility(View.VISIBLE);
            ((TextView)holder.getView(R.id.header)).setText(bean.getFirstPinyin());
        }else {
            if (!TextUtils.equals(bean.getFirstPinyin(),getItem(position-1).getFirstPinyin())){
                holder.getView(R.id.header).setVisibility(View.VISIBLE);
                ((TextView)holder.getView(R.id.header)).setText(bean.getFirstPinyin());
            }else {
                holder.getView(R.id.header).setVisibility(View.GONE);
            }
        }
        ((LinearLayout)holder.getView(R.id.ll_item_create_group_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isSelect()) {
                    bean.setSelect(false);
                    if (onItemHeadListener != null) {
                        onItemHeadListener.head(bean.getHandimg(),bean.getUid());
                    }
                    Glide.with(getmContext()).load(R.drawable.topup_no_check).into((ImageView)holder.getView(R.id.iv_item_create_group_list));
                }else {
                    if (onItemHeadListener != null) {
                        onItemHeadListener.head(bean.getHandimg(),bean.getUid());
                    }
                    bean.setSelect(true);
                    Glide.with(getmContext()).load(R.drawable.addsuccess).into((ImageView)holder.getView(R.id.iv_item_create_group_list));
                }
            }
        });

        holder.itemView.setContentDescription(bean.getFirstPinyin());

    }


    public interface OnItemHeadListener{
        void head(String head,int id);
    }

    private OnItemHeadListener onItemHeadListener;

    public void setOnItemHeadListener(OnItemHeadListener onItemHeadListener) {
        this.onItemHeadListener = onItemHeadListener;
    }

    @Override
    protected int getCustomViewType(int position) {
        return 0;
    }
}
