package com.example.ike.todoapp.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ike on 2018/05/22.
 */
@Module
public class AppModule {
    @Singleton
    @Provides
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
