package com.example.ike.todoapp.di;

import android.app.Application;

import com.example.ike.todoapp.presentation.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ike on 2018/05/22.
 */
@Singleton
@Component(modules =
        {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                MainActivityModule.class,
                ViewModelModule.class
        })
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        public Builder application(Application application);
        public AppComponent build();
    }

    @Override
    void inject(App instance);
}
