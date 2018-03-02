package com.hktstudio.bongda24h.ui.detail;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;

/**
 * Created by Hai on 01/03/2018.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailMvpView> {
    public void onLoadDetail(final String id){
        AsyncTask<Void,Void,NewsDetailEntity> task = new AsyncTask<Void, Void, NewsDetailEntity>() {
            @Override
            protected NewsDetailEntity doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return res.getDetailNews(id);
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(NewsDetailEntity newsDetailEntity) {
                super.onPostExecute(newsDetailEntity);
                if(newsDetailEntity!=null){
                    getMvpView().onGetDetailSuccess(newsDetailEntity);
                }
            }
        };
        task.execute();
    }
}
