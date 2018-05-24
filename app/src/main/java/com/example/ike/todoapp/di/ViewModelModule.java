package com.example.ike.todoapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.ike.todoapp.presentation.MakeTodoViewModel;
import com.example.ike.todoapp.presentation.TodoDetailViewModel;
import com.example.ike.todoapp.presentation.TodoListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by ike on 2018/05/22.
 */
@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodoListViewModel.class)
    ViewModel bindTodoListViewModel(TodoListViewModel todoListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TodoDetailViewModel.class)
    ViewModel bindTodoDetailViewModel(TodoDetailViewModel todoDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MakeTodoViewModel.class)
    ViewModel bindMakeTodoViewModel(MakeTodoViewModel makeTodoViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
