package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.ActivityTodoListBinding;
import com.example.ike.todoapp.di.ViewModelFactory;
import com.example.ike.todoapp.model.Todo;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TodoListActivity extends DaggerAppCompatActivity implements TodoListFragment.OnItemClickListener {

    ActivityTodoListBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    TodoListViewModel viewModel;

    public static final int REQUEST_CODE_ADD_TODO = 111;
    public static final int REQUEST_CODE_UPDATE_TODO = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo_list);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodoListViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addFragment(new TodoListFragment());
        binding.button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MakeTodoActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TODO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_CODE_ADD_TODO || requestCode == REQUEST_CODE_UPDATE_TODO) && resultCode == RESULT_OK) {
            viewModel.loadTodo();
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClick(Todo todo) {
        MakeTodoActivity.startActivityForResult(this, REQUEST_CODE_UPDATE_TODO, todo);
    }
}
