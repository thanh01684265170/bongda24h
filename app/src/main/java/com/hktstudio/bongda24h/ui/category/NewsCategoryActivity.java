package com.hktstudio.bongda24h.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.google.gson.Gson;
import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;
import com.hktstudio.bongda24h.ui.adapter.NewsAdapter;
import com.hktstudio.bongda24h.ui.base.BaseActivity;
import com.hktstudio.bongda24h.ui.detail.NewsDetailActivity;
import com.hktstudio.bongda24h.ui.detail.NewsDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 07/03/2018.
 */

public class NewsCategoryActivity extends BaseActivity implements NewsCategoryMvpView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcv_news_category)
    RecyclerView rcv;
    NewsAdapter adapter;
    NewsCategoryPresenter presenter;
    List<NewsEntity> list;
    CategoryEntity category;
    int itemPerPage= 10;
    boolean loading = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutView(R.layout.activity_news_category);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new NewsCategoryPresenter();
        presenter.attachView(this);
        initNews();
    }
    private void initNews(){
        Gson gson = new Gson();
        category = gson.fromJson(getIntent().getStringExtra("data"),CategoryEntity.class);
        getSupportActionBar().setTitle(category.getDisplayName());
        list = new ArrayList<>();
        adapter = new NewsAdapter(getBaseContext(), list, NewsAdapter.TYPE_NORMAL_NEWS, new ItemOnClick() {
            @Override
            public void onItemClick(int pos, Object obj) {
                if(obj instanceof  NewsEntity){
                    NewsEntity entity = (NewsEntity) obj;
                    Intent t = new Intent(getBaseContext(), NewsDetailActivity.class);
                    t.putExtra("id",entity.getId());
                    startActivity(t);
                }else if(obj instanceof CategoryEntity){
                    CategoryEntity entity = (CategoryEntity) obj;
                    Intent t = new Intent(getBaseContext(), NewsCategoryActivity.class);
                    t.putExtra("data",entity.toString());
                    startActivity(t);
                }
            }
        });
        rcv.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        rcv.setAdapter(adapter);
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcv.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + 1)) {
                    presenter.getNews(category.getId(),list.size()/itemPerPage+1);
                    loading = true;
                }
            }
        });
        presenter.getNews(category.getId(),1);
    }
    @Override
    public void onGeDataSuccess(List<NewsEntity> entity, boolean top) {
        if(top){
            list.clear();
        }
        list.addAll(entity);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onHideLoading() {
        loading =false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
