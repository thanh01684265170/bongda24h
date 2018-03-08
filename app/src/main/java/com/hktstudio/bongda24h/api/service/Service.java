package com.hktstudio.bongda24h.api.service;

import com.hktstudio.bongda24h.entity.CategoryEntity;
import com.hktstudio.bongda24h.entity.NewsDetailEntity;
import com.hktstudio.bongda24h.entity.NewsEntity;
import com.hktstudio.bongda24h.util.Define;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hai on 28/02/2018.
 */

public interface Service {
    @Headers({
        "x-auth-token: "+ Define.AUTH_TOKEN
    })
    @GET("/news-api/hotnews?size=5")
    Call<List<NewsEntity>> getHotNews();
    @GET("/news-api/listnews?size=10")
    Call<List<NewsEntity>> getHomeNews(@Query("page") int page);
    @GET("/news-api/category")
    Call<List<CategoryEntity>> getCategory();
    @GET("/news-api/listnews?size=10")
    Call<List<NewsEntity>> getCategoryNews(@Query("catId") int catId,@Query("page") int page);
    @GET("/news-api/post")
    Call<NewsDetailEntity> getNewsDetail(@Query("id") String id);
}
