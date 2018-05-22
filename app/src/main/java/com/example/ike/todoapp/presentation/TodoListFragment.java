package com.example.ike.todoapp.presentation;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ike.todoapp.databinding.FragmentTodoListBinding;
import com.example.ike.todoapp.di.ViewModelFactory;
import com.example.ike.todoapp.model.Todo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TodoListFragment extends DaggerFragment {

    FragmentTodoListBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    TodoListViewModel viewModel;

    TodoListAdapter adapter;

    public TodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTodoListBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(TodoListViewModel.class);
        viewModel.todo.observe(this, result -> {
            if (result != null && result.data() != null) {
                setRecyclerView((List<Todo>)result.data());
            }
        });
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        final RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new TodoListAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewModel.loadTodo();
        return binding.getRoot();
    }

    private void setRecyclerView(List<Todo> todos) {
        adapter.setData(todos);
    }

}
