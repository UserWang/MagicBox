package com.wjd.magicbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.common.api.APIParams;
import com.android.common.utils.Constants;
import com.android.common.view.SwipeRefreshLayout;
import com.squareup.okhttp.Request;
import com.wjd.magicbox.adapter.NewsViewAdapter;
import com.wjd.magicbox.entity.JokeEntity;
import com.wjd.magicbox.entity.NewsEntity;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjundong on 2015/11/26.
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private View mView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsViewAdapter mNewViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;

    private List<NewsEntity.DetailEntity> mNews = new ArrayList<NewsEntity.DetailEntity>();
    private long max_behot_time = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_main, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.id_swiperefreshlayout);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.id_progressbar);


        configRecyclerView();
        max_behot_time = System.currentTimeMillis();
        loadData();
    }

    private void configRecyclerView() {

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mNewViewAdapter = new NewsViewAdapter(getActivity(), mNews);
        mRecyclerView.setAdapter(mNewViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setOnLoadListener(this);

        mNewViewAdapter.setOnItemClickListener(new NewsViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data) {
                NewsEntity.DetailEntity entity = (NewsEntity.DetailEntity) data;
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", entity.getArticle_url());
                startActivity(intent);
            }
        });

    }

    private void loadData() {
        String news_url = APIParams.NEWS_BASE_URL + "size=" + Constants.PAGE_SIZE + "&max_behot_time=" + max_behot_time;
        new OkHttpRequest.Builder()
                .url(news_url)
                .get(new ResultCallback<NewsEntity>() {
                    @Override
                    public void onResponse(NewsEntity response) {
                        mNews = response.getDetail();
                        mNewViewAdapter.mNews.addAll(mNews);
                        mNewViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        //TODO:错误提示
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showProgressBar();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        hideProgressBar();
                    }
                });
    }


    @Override
    public void onRefresh() {
        max_behot_time = System.currentTimeMillis();
        mNewViewAdapter.mNews.clear();
        loadData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoad() {
        max_behot_time = mNews.get(mNews.size() - 1).getBehot_time();
        loadData();
        mSwipeRefreshLayout.setLoading(false);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}
