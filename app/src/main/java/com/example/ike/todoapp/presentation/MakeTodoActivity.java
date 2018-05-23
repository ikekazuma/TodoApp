package com.example.ike.todoapp.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityMakeTodoBinding;

import dagger.android.support.DaggerAppCompatActivity;

public class MakeTodoActivity extends DaggerAppCompatActivity {

    ActivityMakeTodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_make_todo);
        addFragment(new MakeTodoFragment());
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
