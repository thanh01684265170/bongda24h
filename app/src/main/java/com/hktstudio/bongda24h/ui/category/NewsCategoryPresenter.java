package com.hktstudio.bongda24h.ui.category;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hai on 07/03/2018.
 */

public class NewsCategoryPresenter extends BasePresenter<NewsCategoryMvpView> {
    public void getNews(final int id, final int page){
        AsyncTask<Void,Void,List<NewsEntity>> task = new AsyncTask<Void, Void, List<NewsEntity>>() {
            @Override
            protected List<NewsEntity> doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return res.getCategoryNews(id,page);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<NewsEntity> newsEntities) {
                super.onPostExecute(newsEntities);
                if(newsEntities != null){
                    getMvpView().onGeDataSuccess(newsEntities,page==1);
                }
                getMvpView().onHideLoading();
            }
        };
        task.execute();
    }
}
