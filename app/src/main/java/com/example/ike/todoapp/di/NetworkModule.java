package com.example.ike.todoapp.di;

import com.example.ike.todoapp.data.api.TodoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.example.ike.todoapp.BuildConfig.API_BASE_URL;
import static com.example.ike.todoapp.BuildConfig.API_KEY;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message)).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader("x-api-key", API_KEY).build()))
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public TodoAPI provideKondateteianApiV1(Retrofit retrofit) {
        return retrofit.create(TodoAPI.class);
    }

}
