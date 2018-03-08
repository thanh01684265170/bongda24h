package com.hktstudio.bongda24h.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.hktstudio.bongda24h.R;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.interfaces.ItemOnClick;
import com.hktstudio.bongda24h.ui.adapter.NewsAdapter;
import com.hktstudio.bongda24h.ui.base.BaseActivity;
import com.hktstudio.bongda24h.ui.category.NewsCategoryActivity;
import com.hktstudio.bongda24h.ui.detail.NewsDetailActivity;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 08/03/2018.
 */

public class SearchNewsActivity extends BaseActivity implements SearchNewsMvpView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.rcv_search)
    RecyclerView rcv;
    SearchNewsPresenter presenter;
    NewsAdapter adapter;
    List<NewsEntity> list;
    int itemPerPage= 10;
    boolean loading = false;
    String key = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        presenter = new SearchNewsPresenter();
        presenter.attachView(this);
        initNews();
    }
    protected void initNews(){
        list = new ArrayList<>();
        adapter = new NewsAdapter(this, list, NewsAdapter.TYPE_NORMAL_NEWS, new ItemOnClick() {
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
        rcv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcv.setAdapter(adapter);
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcv.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + 1)) {
                    presenter.search(key,list.size()/itemPerPage+1);
                    loading = true;
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.showSearch();
        searchView.showVoice(false);
        initSearch();
        return true;
    }
    protected void initSearch(){
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                key = query;
                presenter.search(query,1);
                loading = true;
                hideKeybroad();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                finish();
            }
        });
    }
    @Override
    public void onGetDataSuccess(List<NewsEntity> entities,boolean top) {
        if(top){
            list.clear();
        }
        list.addAll(entities);
        adapter.notifyDataSetChanged();
    }
    protected void hideKeybroad(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onHideLoading() {
        loading = false;
    }
}
