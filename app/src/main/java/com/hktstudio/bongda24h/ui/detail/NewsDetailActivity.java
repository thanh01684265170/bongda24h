package com.hktstudio.bongda24h.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.interfaces.WebAppInterface;
import com.hktstudio.bongda24h.ui.base.BaseActivity;
import com.hktstudio.bongda24h.util.UtilTime;

import org.w3c.dom.Entity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Hai on 01/03/2018.
 */

public class NewsDetailActivity extends BaseActivity implements NewsDetailMvpView {
    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.img_poster) ImageView img;
    @BindView(R.id.tv_news_time_ago) TextView tvTimeAgo;
    @BindView(R.id.tv_news_category) TextView tvCategory;
    @BindView(R.id.tv_news_title) TextView tvTitle;
    @BindView(R.id.tv_news_des) TextView tvDes;
    @BindView(R.id.toolbar) Toolbar toolbar;
    NewsDetailPresenter presenter;
    NewsDetailEntity entity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutView(R.layout.activity_detail_news);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new NewsDetailPresenter();
        presenter.attachView(this);
        initNews();
    }

    protected void initNews() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        presenter.onLoadDetail(getIntent().getStringExtra("id"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public void onGetDetailSuccess(NewsDetailEntity entity) {
        this.entity = entity;
        //load item
        Glide.with(this).load(entity.getPostImage()).into(img);
        tvCategory.setText(entity.getCategory().getDisplayName().toUpperCase()+" • ");
        tvDes.setText(entity.getDescription());
        tvTimeAgo.setText(UtilTime.timeAgo(entity.getLastModifyDate()));
        tvTitle.setText(entity.getTitle());
        toolbar.setTitle(entity.getCategory().getDisplayName());
       // getSupportActionBar().setTitle(entity.getCategory().getDisplayName());
        //load web
        StringBuilder data = new StringBuilder();
        data.append("<HTML><HEAD><LINK href=\"default.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
        data.append(entity.getContent().replaceAll("<img","<img onclick=\"clickImage(this)\""));
        data.append("</body></HTML>");
        data.append("<script type=\"text/javascript\" src=\"action.js\"></script>");
        webView.loadDataWithBaseURL("file:///android_asset/", data.toString(), "text/html", "utf-8", null);
        intVideoSize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    void intVideoSize(){
        DisplayMetrics metrix = getResources().getDisplayMetrics();
        int width = metrix.widthPixels;
        int height = metrix.heightPixels;
        float density = metrix.density;

        String func = "javascript:changeIframeSize(" +
                ")";
//                (int)(height / density - 75 ) +
//                ", " +
//                (int)(width / density) +
//                ")";
        Timber.e(func);
//            webView.loadUrl(func);

            webView.loadUrl("javascript:testEcho()");
    }
}
