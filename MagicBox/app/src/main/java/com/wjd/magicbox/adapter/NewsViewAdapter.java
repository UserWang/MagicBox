package com.wjd.magicbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.common.utils.TimeUtils;
import com.wjd.magicbox.R;
import com.wjd.magicbox.entity.NewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjundong on 2015/11/27.
 */
public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> implements View.OnClickListener{

    public Context mContext;
    public LayoutInflater mLayoutInflater;
    public List<NewsEntity.DetailEntity> mNews = new ArrayList<NewsEntity.DetailEntity>();
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public NewsViewAdapter(Context mContext, List<NewsEntity.DetailEntity> mNews) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mNews = mNews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.news_item_main, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        mView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsEntity.DetailEntity detailEntity = mNews.get(position);
        if (null == detailEntity) {
            return;
        }

        long behot_time = detailEntity.getBehot_time();

        String time = TimeUtils.formatMillisencond(behot_time);
        holder.mTitle.setText(detailEntity.getTitle());
        holder.mSource.setText(detailEntity.getSource() + " " + time);

        holder.itemView.setTag(detailEntity);
    }



    @Override
    public int getItemCount() {
        return mNews == null ? 0 : mNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public TextView mTitle;
        public TextView mSource;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.id_content_tv);
            mSource = (TextView) itemView.findViewById(R.id.id_author_tv);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,v.getTag());
        }
    }

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , Object data);
    }
}
