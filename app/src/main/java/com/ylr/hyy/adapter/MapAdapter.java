package com.ylr.hyy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.SizeUtils;
import com.ylr.hyy.R;

import java.util.ArrayList;
import java.util.List;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder> {
    private Context mContext;


    public MapAdapter(Context context){
        mContext =context;
        list = new ArrayList<>();
    }

    private List<PoiItem> list;

    public void setList(List<PoiItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_map,parent,false);
        return new ViewHolder(view);
    }

    private static final String TAG = "MapAdapter";

    private int pos = 0;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: "+list.get(position));
        if (position == pos) {
            onBackListener.back(list.get(position));
            holder.iv.setVisibility(View.VISIBLE);
        }else {
            holder.iv.setVisibility(View.GONE);
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(list.get(position).getTitle()+"\n"+list.get(position).getSnippet());
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),list.get(position).getTitle().length(),builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(SizeUtils.sp2px(13)),list.get(position).getTitle().length(),builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv.setText(builder);

        holder.tv.setOnClickListener(v -> {
            if (onBackListener != null) {
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
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_map_text);
            iv = itemView.findViewById(R.id.iv_item_map_select);
        }
    }

    public interface OnBackListener{
        void back(PoiItem poiItem);
    }

    private OnBackListener onBackListener;

    public void setOnBackListener(OnBackListener onBackListener) {
        this.onBackListener = onBackListener;
    }
}
