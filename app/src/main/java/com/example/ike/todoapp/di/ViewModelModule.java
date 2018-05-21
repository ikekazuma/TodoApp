package com.example.ike.todoapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.ike.todoapp.presentation.MainViewModel;

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
    @ViewModelKey(MainViewModel.class)
    ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
