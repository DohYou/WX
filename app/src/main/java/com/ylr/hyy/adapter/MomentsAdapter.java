package com.ylr.hyy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ParseException;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.ylr.hyy.R;
import com.ylr.hyy.mvp.model.MomentsModel;
import com.ylr.hyy.utils.NinthPalaceViewGroup;
import com.ylr.hyy.utils.RoundImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder> {
    private Context mContext;
    private int pos;
    public MomentsAdapter(Context context){
        mContext = context;
        list  = new ArrayList<>();
    }

    private List<MomentsModel.DataBean> list;

    public void setList(List<MomentsModel.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<MomentsModel.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void upData(MomentsModel.DataBean dataBean){
        this.list.remove(pos);
        this.list.add(pos,dataBean);
        notifyItemChanged(pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_moments,parent,false);
        return new ViewHolder(view);
    }

    private static final String TAG = "MomentsAdapter";
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(list.get(position).getHandimg()).into(holder.roundImageView);
        holder.tvName.setText(list.get(position).getNickname());
        holder.tvContent.setText(list.get(position).getWrittenwords());
        holder.ninthPalaceViewGroup.clearImg();
        holder.tvTime.setText(getDateToString(list.get(position).getCreattime()));

        if (!TextUtils.isEmpty(list.get(position).getImgs())) {
            String [] img = list.get(position).getImgs().split(",");
            holder.ninthPalaceViewGroup.addChild(img);
        }
        if (TextUtils.isEmpty(list.get(position).getVideos())) {
            holder.ivVideo.setVisibility(View.GONE);
        }else {
            holder.ivVideo.setVisibility(View.VISIBLE);
        }

        if (list.get(position).getLikeuself() == 0) {
            Glide.with(mContext).load(R.drawable.heart_nocheck).into(holder.ivLike);
        }else {
            Glide.with(mContext).load(R.drawable.heart_check).into(holder.ivLike);
        }

        //点赞
        if (list.get(position).getFabulous().size() != 0) {
            Drawable drawableLeft = mContext.getDrawable(R.drawable.heart_check);
            Log.i(TAG, "onBindViewHolder: "+drawableLeft.getMinimumWidth());
            drawableLeft.setBounds(0, 0, SizeUtils.dp2px(16),SizeUtils.dp2px(16));
            holder.tvLikeNum.setCompoundDrawables(drawableLeft,null, null, null);

            holder.tvLikeNum.setVisibility(View.VISIBLE);
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < list.get(position).getFabulous().size(); i++) {
                buffer.append(list.get(position).getFabulous().get(i).getUsernickname()+"、");
            }
            SpannableStringBuilder spannable = new SpannableStringBuilder(buffer.toString());
            holder.tvLikeNum.setMovementMethod(LinkMovementMethod.getInstance());
            int start = 0;
            int end = 0;
            for (int i = 0; i < list.get(position).getFabulous().size(); i++) {
                if (i != 0) {
                    start += list.get(position).getFabulous().get(i - 1).getUsernickname().length() + 1;
                }
                end += list.get(position).getFabulous().get(i).getUsernickname().length() + 1;
                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        avoidHintColor(widget);
                    }
                }, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.tvLikeNum.setText(spannable);
        }else {
            holder.tvLikeNum.setVisibility(View.GONE);
        }

        //评论
        holder.linearLayout.removeAllViews();
        if (list.get(position).getEvea().size() != 0) {
            for (int i = 0; i < list.get(position).getEvea().size() ; i++) {
                TextView textView = new TextView(mContext);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(0,10,0,10);
                textView.setTextSize(14);
                textView.setTextColor(Color.parseColor("#333333"));
                SpannableStringBuilder builder = new SpannableStringBuilder(list.get(position).getEvea().get(i).getUsernickname() + " ：" + list.get(position).getEvea().get(i).getContent());
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")),list.get(position).getEvea().get(i).getUsernickname().length(),builder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(builder);
                holder.linearLayout.addView(textView);
            }

        }

        holder.tvComment.setText(list.get(position).getEvea().size()+"");
        holder.tvLike.setText(list.get(position).getFabulous().size()+"");

        holder.tvLike.setOnClickListener(view -> {
            pos = position;
            onItemClickListener.like(list.get(position).getId(),list.get(position).getLikeuself(),list.get(position));
        });

        holder.tvComment.setOnClickListener(view -> {
            pos = position;
            onItemClickListener.reply(list.get(position).getId(),list.get(position).getNickname(),list.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        RoundImageView roundImageView;
        TextView tvName,tvTime,tvContent,tvComment,tvLike,tvLikeNum;
        ImageView ivVideo,ivComment,ivLike;
        NinthPalaceViewGroup ninthPalaceViewGroup;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLikeNum = itemView.findViewById(R.id.tv_item_like_num);
            roundImageView = itemView.findViewById(R.id.riv_item_moments_head);
            tvName = itemView.findViewById(R.id.tv_item_moments_name);
            tvTime = itemView.findViewById(R.id.tv_item_moments_time);
            tvContent = itemView.findViewById(R.id.tv_item_moments_content);
            tvComment = itemView.findViewById(R.id.tv_item_comments);
            tvLike = itemView.findViewById(R.id.tv_item_zan);
            ivVideo = itemView.findViewById(R.id.iv_moments_video);
            ivComment = itemView.findViewById(R.id.iv_item_comments);
            ivLike = itemView.findViewById(R.id.iv_item_zan);
            ninthPalaceViewGroup = itemView.findViewById(R.id.ninth_item);
            linearLayout = itemView.findViewById(R.id.ll_comment_list);
        }
    }

    private String DateFormat(String data){
        String[] strs=data.split("T");
        return strs[0];
    }


    private void avoidHintColor(View view){
        if(view instanceof TextView)
            ((TextView)view).setHighlightColor(mContext.getResources().getColor(android.R.color.transparent));
    }

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @return
     */
    private String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return DateFormat(format.format(date));
    }


    public interface OnItemClickListener{
        void like(int id, int type, MomentsModel.DataBean dataBean);
        void reply(int id,String name,MomentsModel.DataBean dataBean);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
