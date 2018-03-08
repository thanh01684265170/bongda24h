package com.hktstudio.bongda24h.ui.home.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;
import com.hktstudio.bongda24h.ui.adapter.NewsAdapter;
import com.hktstudio.bongda24h.ui.category.NewsCategoryActivity;
import com.hktstudio.bongda24h.ui.detail.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 28/02/2018.
 */

public class HomeFragment extends Fragment implements HomeMvpView,ItemOnClick{
    @BindView(R.id.rcv_hot_news)
    RecyclerView rcvHotNews;
    @BindView(R.id.rcv_home_news)
    RecyclerView rcvHomeNews;
    @BindView(R.id.scroll_home)
    ScrollView scrollView;
    NewsAdapter hotAdapter,homeAdapter;
    List<NewsEntity> hotNews,homeNews;
    HomePresenter presenter;
    int itemPerPage= 10;
    boolean loading = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter();
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,rootView);
        initHotNews();
        initHomeNews();
        return rootView;
    }

    /**
     * get Hot news
     */
    protected void initHotNews(){
        hotNews = new ArrayList<>();
        hotAdapter = new NewsAdapter(getContext(),hotNews,NewsAdapter.TYPE_HOT_NEWS,this);
        rcvHotNews.setAdapter(hotAdapter);
        rcvHotNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        presenter.getHotNews();
    }

    /**
     * get Home news
     */
    protected void initHomeNews(){
        homeNews = new ArrayList<>();
        homeAdapter = new NewsAdapter(getContext(),homeNews,NewsAdapter.TYPE_NORMAL_NEWS,this);
        rcvHomeNews.setAdapter(homeAdapter);
        rcvHomeNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView != null) {
                    if (scrollView.getChildAt(0).getBottom() <= (scrollView.getHeight() + scrollView.getScrollY())) {
                        //scroll view is at bottom
                        if( !loading ){
                            presenter.getHomeNews(homeNews.size()/itemPerPage+1);
                            loading = true;
                        }
                    } else {
                        //scroll view is not at bottom
                    }
                }
            }
        });
        presenter.getHomeNews(1);
    }
    @Override
    public void onGetHotNewsSuccess(List<NewsEntity> news) {
        hotNews.clear();
        hotNews.addAll(news);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetHomeNewsSuccess(List<NewsEntity> news, boolean top) {
        if(top){
            homeNews.clear();
        }
        homeNews.addAll(news);
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoading() {
        loading = true;
    }

    @Override
    public void onHideLoading() {
        loading = false;
    }

    @Override
    public void onItemClick(int pos, Object obj) {
        if(obj instanceof  NewsEntity){
            NewsEntity entity = (NewsEntity) obj;
            Intent t = new Intent(getContext(), NewsDetailActivity.class);
            t.putExtra("id",entity.getId());
            getContext().startActivity(t);
        }else if(obj instanceof CategoryEntity){
            CategoryEntity entity = (CategoryEntity) obj;
            Intent t = new Intent(getContext(), NewsCategoryActivity.class);
            t.putExtra("data",entity.toString());
            getContext().startActivity(t);
        }
    }
}
