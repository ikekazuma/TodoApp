package com.example.ike.todoapp.di;

import android.app.Application;
import android.content.Context;

import com.example.ike.todoapp.data.repository.TodoRepository;
import com.example.ike.todoapp.data.repository.TodoRepositoryImpl;
import com.example.ike.todoapp.data.repository.UserRepository;
import com.example.ike.todoapp.data.repository.UserRepositoryImpl;

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

    @Singleton
    @Provides
    TodoRepository provideTodoRepository(TodoRepositoryImpl todoRepository) {
        return todoRepository;
    }

    @Singleton
    @Provides
    UserRepository provideUserRepository(UserRepositoryImpl userRespository) {
        return userRespository;
    }

}
