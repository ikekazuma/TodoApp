package com.example.ike.todoapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityTodoDetailBinding;
import com.example.ike.todoapp.model.Todo;

import dagger.android.support.DaggerAppCompatActivity;

public class TodoDetailActivity extends DaggerAppCompatActivity {

    ActivityTodoDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Todo todo = getIntent().getParcelableExtra("todo");
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

    public static void start(Context context, Todo todo) {
        Intent intent = new Intent(context, TodoDetailActivity.class);
        intent.putExtra("todo", todo);
        context.startActivity(intent);
    }
}
