package com.example.ike.todoapp.di;

import com.example.ike.todoapp.presentation.TodoDetailActivity;
import com.example.ike.todoapp.presentation.TodoDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface TodoDetailActivityModule {

    @ContributesAndroidInjector
    public TodoDetailActivity contributeTodoDetailActivity();

    @ContributesAndroidInjector
    public TodoDetailFragment contributeTodoDetailFragment();
}
