package com.hktstudio.bongda24h.ui.search;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hai on 08/03/2018.
 */

public class SearchNewsPresenter extends BasePresenter<SearchNewsMvpView> {
    private List<CategoryEntity> categoryEntities;

    public void setCategory(List<CategoryEntity> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    public void search(final String key, final int page){
        AsyncTask<Void,Void,List<NewsEntity>> task = new AsyncTask<Void, Void, List<NewsEntity>>() {
            @Override
            protected List<NewsEntity> doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return res.searchNews(key,page);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<NewsEntity> newsEntities) {
                super.onPostExecute(newsEntities);
                if(getMvpView()==null)
                    return;
                if(newsEntities!=null){
                    getMvpView().onGetDataSuccess(fixListCategory(newsEntities),page==1);
                }
                getMvpView().onHideLoading();
            }
        };
        task.execute();
    }
    public List<NewsEntity> fixListCategory(List<NewsEntity> newsEntities) {
        for (int i=0;i<newsEntities.size();i++){
            newsEntities.get(i).getCategory().setDisplayName(nameCategory(newsEntities.get(i).getCategory().getId()));
        }
        return newsEntities;
    }
    protected String nameCategory(int id){
        for (CategoryEntity c:categoryEntities){
            if(id == c.getId())
                return c.getDisplayName();
        }
        return "";
    }
}
