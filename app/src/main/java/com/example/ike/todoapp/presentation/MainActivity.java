package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityMainBinding;
import com.example.ike.todoapp.di.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.test();
    }
}
