package com.example.ike.todoapp.di;

import com.example.ike.todoapp.presentation.TodoListActivity;
import com.example.ike.todoapp.presentation.TodoListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ike on 2018/05/22.
 */
@Module
public interface TodoListActivityModule {

    @ContributesAndroidInjector
    public TodoListActivity contributeTodoListActivity();

    @ContributesAndroidInjector
    public TodoListFragment contributeTodoListFragment();
}
