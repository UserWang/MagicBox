package com.wjd.magicbox;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.common.api.APIParams;
import com.android.common.utils.Constants;
import com.android.common.view.SwipeRefreshLayout;
import com.squareup.okhttp.Request;
import com.wjd.magicbox.adapter.JokeViewAdapter;
import com.wjd.magicbox.entity.JokeEntity;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 笑话段子
 * Created by wangjundong on 2015/11/26.
 */
public class JokeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private View mView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private JokeViewAdapter mJockViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;

    private List<JokeEntity.DetailEntity> mJokes = new ArrayList<JokeEntity.DetailEntity>();
    private int page = 0;


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
        loadData();
    }

    private void configRecyclerView() {

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mJockViewAdapter = new JokeViewAdapter(getActivity(), mJokes);
        mRecyclerView.setAdapter(mJockViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setOnLoadListener(this);

    }

    private void loadData() {
        String joke_url = APIParams.JOKE_BASE_URL + "size=" + Constants.PAGE_SIZE + "&page=" + page;
        new OkHttpRequest.Builder()
                .url(joke_url)
                .get(new ResultCallback<JokeEntity>() {
                    @Override
                    public void onResponse(JokeEntity response) {
                        mJokes = response.getDetail();
                        mJockViewAdapter.mJokes.addAll(mJokes);
                        mJockViewAdapter.notifyDataSetChanged();
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
        page = 0;
        mJockViewAdapter.mJokes.clear();
        loadData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoad() {
        page++;
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
