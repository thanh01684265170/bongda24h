package com.hktstudio.bongda24h.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.NewsDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 01/03/2018.
 */

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailMvpView{
    @BindView(R.id.webview)
    WebView webView;
    NewsDetailPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ButterKnife.bind(this);
        presenter = new NewsDetailPresenter();
        presenter.attachView(this);
        initNews();
    }
    protected void initNews(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        presenter.onLoadDetail(getIntent().getStringExtra("id"));
    }

    @Override
    public void onGetDetailSuccess(NewsDetailEntity entity) {
        webView.loadData(entity.getContent(), "text/html; charset=UTF-8", null);
    }
}
