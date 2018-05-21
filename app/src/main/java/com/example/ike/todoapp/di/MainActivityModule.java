package com.example.ike.todoapp.di;

import com.example.ike.todoapp.presentation.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ike on 2018/05/22.
 */
@Module
public interface MainActivityModule {
    @ContributesAndroidInjector
    public MainActivity contoributeMainActivity();
}
