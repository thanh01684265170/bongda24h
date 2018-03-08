package com.hktstudio.bongda24h.ui.search;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hai on 08/03/2018.
 */

public class SearchNewsPresenter extends BasePresenter<SearchNewsMvpView> {
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
                if(newsEntities!=null)
                    getMvpView().onGetDataSuccess(newsEntities,page==1);
                getMvpView().onHideLoading();
            }
        };
        task.execute();
    }
}
