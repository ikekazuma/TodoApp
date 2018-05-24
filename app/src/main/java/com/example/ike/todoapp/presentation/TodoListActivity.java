package com.example.ike.todoapp.presentation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityTodoListBinding;

import dagger.android.support.DaggerAppCompatActivity;

public class TodoListActivity extends DaggerAppCompatActivity {

    ActivityTodoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addFragment(new TodoListFragment());
        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MakeTodoActivity.class);
            startActivity(intent);
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
