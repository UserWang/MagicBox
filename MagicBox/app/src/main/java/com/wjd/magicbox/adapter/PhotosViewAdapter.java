package com.wjd.magicbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjd.magicbox.R;
import com.wjd.magicbox.entity.JokeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjundong on 2015/11/27.
 */
public class PhotosViewAdapter extends RecyclerView.Adapter<PhotosViewAdapter.ViewHolder> {

    public Context mContext;
    public List<String> mDatas;
    public LayoutInflater mLayoutInflater;

    public JokeEntity jokeEntity;
    public List<JokeEntity.DetailEntity> jokes;

    public PhotosViewAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = new ArrayList<String>();
        for (int i='A';i<='Z';i++){
            mDatas.add((char)i+"");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.item_main,parent,false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
