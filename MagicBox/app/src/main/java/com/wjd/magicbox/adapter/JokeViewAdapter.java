package com.wjd.magicbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjd.magicbox.R;
import com.wjd.magicbox.entity.JokeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjundong on 2015/11/27.
 */
public class JokeViewAdapter extends RecyclerView.Adapter<JokeViewAdapter.ViewHolder> {

    public Context mContext;
    public LayoutInflater mLayoutInflater;

    public List<JokeEntity.DetailEntity> mJokes = new ArrayList<JokeEntity.DetailEntity>();

    public JokeViewAdapter(Context mContext, List<JokeEntity.DetailEntity> mJokes) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mJokes = mJokes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.joke_item_main, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JokeEntity.DetailEntity detailEntity = mJokes.get(position);
        if (null == detailEntity) {
            return;
        }

        holder.mContent.setText(detailEntity.getContent());
        holder.mAuthor.setText("投稿by：" + detailEntity.getAuthor());
        String picUrl = detailEntity.getPicUrl();
        if (!TextUtils.isEmpty(picUrl)) {
            holder.mPic.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(picUrl).into(holder.mPic);
        }else {
            holder.mPic.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mJokes == null ? 0 : mJokes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mContent;
        public TextView mAuthor;
        public ImageView mPic;

        public ViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.id_content_tv);
            mAuthor = (TextView) itemView.findViewById(R.id.id_author_tv);
            mPic = (ImageView) itemView.findViewById(R.id.id_pic_iv);
        }
    }
}
