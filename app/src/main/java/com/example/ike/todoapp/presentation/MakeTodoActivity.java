package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityMakeTodoBinding;
import com.example.ike.todoapp.di.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MakeTodoActivity extends DaggerAppCompatActivity {

    ActivityMakeTodoBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    MakeTodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_make_todo);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MakeTodoViewModel.class);
        viewModel.add.observe(this, result -> {
            if (result == null || result.isLoading()) {
                return;
            }
            String message = result.throwable() == null ? "成功しました" : "失敗しました";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addFragment(new MakeTodoFragment());
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (viewModel.isAdded) {
                setResult(RESULT_OK);
            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            if (viewModel.isAdded) {
                setResult(RESULT_OK);
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
