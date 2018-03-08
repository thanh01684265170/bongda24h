package com.hktstudio.bongda24h.ui.home.category;

import android.os.AsyncTask;

import com.hktstudio.bongda24h.api.response.NewsResponse;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.ui.base.BasePresenter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hai on 01/03/2018.
 */

public class CategoryPresenter extends BasePresenter<CategoryMvpView> {
    public void loadCategory(){
        AsyncTask<Void,Void,List<CategoryEntity>> task = new AsyncTask<Void, Void, List<CategoryEntity>>() {
            @Override
            protected List<CategoryEntity> doInBackground(Void... voids) {
                NewsResponse res = new NewsResponse();
                try {
                    return  res.getCategory();
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<CategoryEntity> categoryEntities) {
                super.onPostExecute(categoryEntities);
                if(getMvpView()==null)
                    return;
                if(categoryEntities!=null){
                    getMvpView().onLoadSuccess(categoryEntities);
                }else{
                    getMvpView().onLoadFail();
                }
            }
        };
        task.execute();
    }
}
