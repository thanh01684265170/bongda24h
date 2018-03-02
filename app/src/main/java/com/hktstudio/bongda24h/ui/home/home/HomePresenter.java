package com.hktstudio.bongda24h.ui.home.home;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hai on 28/02/2018.
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {
    public void getHotNews(){
        AsyncTask<Void,Void,List<NewsEntity>> task = new AsyncTask<Void, Void, List<NewsEntity>>() {
            @Override
            protected List<NewsEntity> doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return res.getHotNews();
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<NewsEntity> newsEntities) {
                super.onPostExecute(newsEntities);
                if(newsEntities!=null){
                    getMvpView().onGetHotNewsSuccess(newsEntities);
                }
            }
        };
        task.execute();
    }
    public void getHomeNews(final int page){
        AsyncTask<Void,Void,List<NewsEntity>> task = new AsyncTask<Void, Void, List<NewsEntity>>() {
            @Override
            protected List<NewsEntity> doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return res.getHomeNews(page);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<NewsEntity> newsEntities) {
                super.onPostExecute(newsEntities);
                if(newsEntities!=null){
                    getMvpView().onGetHomeNewsSuccess(newsEntities,page==1?true:false);
                }
                getMvpView().onHideLoading();
            }
        };
        task.execute();
        getMvpView().onLoading();
    }
}
