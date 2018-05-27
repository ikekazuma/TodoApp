package com.example.ike.todoapp.di;

import com.example.ike.todoapp.presentation.MakeTodoActivity;
import com.example.ike.todoapp.presentation.MakeTodoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MakeTodoActivityModule {
    @ContributesAndroidInjector
    public MakeTodoActivity contributeMakeTodoActivity();

    @ContributesAndroidInjector
    public MakeTodoFragment contributeMakeTodoFragment();
}
