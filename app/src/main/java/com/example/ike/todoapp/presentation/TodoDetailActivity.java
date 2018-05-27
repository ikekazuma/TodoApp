package com.example.ike.todoapp.presentation;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
import com.example.ike.todoapp.databinding.ActivityTodoDetailBinding;
import com.example.ike.todoapp.di.ViewModelFactory;
import com.example.ike.todoapp.model.Todo;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TodoDetailActivity extends DaggerAppCompatActivity {

    ActivityTodoDetailBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    TodoDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodoDetailViewModel.class);
        viewModel.delete.observe(this, result -> {
            if (result != null && result.data() != null) {
                setResult(RESULT_OK);
                finish();
            } else if (result != null && result.throwable() != null ) {
                Toast.makeText(this, "失敗しました", Toast.LENGTH_LONG).show();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Todo todo = getIntent().getParcelableExtra("todo");
        binding.button.setOnClickListener(view -> {
            viewModel.onDeleteButtonClick(todo);
        });
        addFragment(TodoDetailFragment.newInstance(todo));
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
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startActivityForResult(Context context, int requestCode, Todo todo) {
        Intent intent = new Intent(context, TodoDetailActivity.class);
        intent.putExtra("todo", todo);
        ((Activity)context).startActivityForResult(intent, requestCode);
    }
}
