package com.hddev.task1.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hddev.task1.Interfaces.PostApplicationScope;
import com.hddev.task1.service.APIService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hddev.task1.util.Constants.BASE_URL;

@Module(includes = OkHttpClientModule.class)
public class PostModule {

    @Provides
    public APIService apiService (Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @PostApplicationScope
    @Provides
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory
                                , RxJava2CallAdapterFactory rxJava2CallAdapterFactory
                                , Gson gson
                                , OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
