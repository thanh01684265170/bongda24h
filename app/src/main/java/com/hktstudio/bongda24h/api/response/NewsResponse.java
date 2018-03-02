package com.hktstudio.bongda24h.api.response;

import com.hktstudio.bongda24h.api.repository.Repository;
import com.hktstudio.bongda24h.api.service.Service;
import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Hai on 28/02/2018.
 */

public class NewsResponse extends Repository {
    Service service;

    public NewsResponse() {
        this.service = this.getRetrofit().create(Service.class);
    }
    public List<NewsEntity> getHotNews() throws IOException {
        Call<List<NewsEntity>> call = this.service.getHotNews();
        return call.execute().body();
    }
    public List<NewsEntity> getHomeNews(int page) throws IOException {
        Call<List<NewsEntity>> call = this.service.getHomeNews(page);
        return call.execute().body();
    }
    public List<NewsEntity> getCategoryNews(int id,int page) throws IOException {
        Call<List<NewsEntity>> call = this.service.getCategoryNews(id,page);
        return call.execute().body();
    }
    public List<CategoryEntity> getCategory() throws IOException {
        Call<List<CategoryEntity>> call = this.service.getCategory();
        return call.execute().body();
    }
    public NewsDetailEntity getDetailNews(String id) throws IOException {
        Call<NewsDetailEntity> call = this.service.getNewsDetail(id);
        return call.execute().body();
    }
}
