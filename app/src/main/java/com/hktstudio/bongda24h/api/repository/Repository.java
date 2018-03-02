package com.hktstudio.bongda24h.api.repository;

import com.hktstudio.bongda24h.util.Define;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hai on 28/02/2018.
 */

public class Repository {
    Retrofit retrofit;

    public Repository() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("x-auth-token", Define.AUTH_TOKEN)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Define.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
