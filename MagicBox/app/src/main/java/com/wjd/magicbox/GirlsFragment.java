package com.wjd.magicbox;

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

import com.android.common.api.APIParams;
import com.android.common.utils.Constants;
import com.android.common.view.SwipeRefreshLayout;
import com.squareup.okhttp.Request;
import com.wjd.magicbox.adapter.PhotosViewAdapter;
import com.wjd.magicbox.entity.JokeEntity;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

/**
 * Created by wangjundong on 2015/11/26.
 */
public class GirlsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {

    private View mView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private PhotosViewAdapter mPhotosViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        configRecyclerView();
        loadData();
    }

    private void configRecyclerView() {

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mPhotosViewAdapter = new PhotosViewAdapter(getActivity());
        mRecyclerView.setAdapter(mPhotosViewAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setOnLoadListener(this);

    }


    private void loadData() {
        String girls_url = APIParams.GIRLS_URL + "num=" + Constants.PAGE_SIZE + "&page=" + page;
        Log.d("Girls--", "api = " + girls_url);
//        new OkHttpRequest.Builder()
//                .url(girls_url)
//                .get(new ResultCallback<JokeEntity>() {
//                    @Override
//                    public void onResponse(JokeEntity response) {
//
//                    }
//
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//                });
    }


    @Override
    public void onRefresh() {


    }

    @Override
    public void onLoad() {

    }

}
