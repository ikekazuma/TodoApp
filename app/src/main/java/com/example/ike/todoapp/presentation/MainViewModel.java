package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Created by ike on 2018/05/22.
 */

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> test = new MutableLiveData<>();

    @Inject
    public MainViewModel() {

    }

    public void test() {
        test.postValue("test");
    }
}
