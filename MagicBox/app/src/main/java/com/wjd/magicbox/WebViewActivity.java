package com.wjd.magicbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by wangjundong on 2015/12/10.
 */
public class WebViewActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private WebView mWebView;
    private ProgressBar mPb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        findViews();
        configViews();
    }

    private void findViews() {
        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mWebView = (WebView) findViewById(R.id.id_webview);
        mPb = (ProgressBar) findViewById(R.id.id_progressbar);
    }

    private void configViews() {
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.mipmap.icon_back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showProgress();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollContainer(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.loadUrl(getIntent().getStringExtra("url"));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgress();
            }
        });
    }

    private void showProgress() {
        mPb.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mPb.setVisibility(View.GONE);
    }
}
