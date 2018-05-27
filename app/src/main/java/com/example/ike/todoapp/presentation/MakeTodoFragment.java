package com.example.ike.todoapp.presentation;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ike.todoapp.databinding.FragmentMakeTodoBinding;
import com.example.ike.todoapp.di.ViewModelFactory;
import com.example.ike.todoapp.model.Todo;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MakeTodoFragment extends DaggerFragment {

    FragmentMakeTodoBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    MakeTodoViewModel viewModel;

    public MakeTodoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMakeTodoBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MakeTodoViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        viewModel.add.observe(this, result -> {
            if (result != null && result.data() != null) {
                // 成功したらクリアする
                binding.title.setText("");
                binding.content.setText("");
            }
        });
        binding.button.setOnClickListener(view -> {
            if (binding.title.getText().toString().isEmpty() || binding.content.getText().toString().isEmpty()) {
                return;
            }
            Todo todo = new Todo();
            todo.title = binding.title.getText().toString();
            todo.content = binding.content.getText().toString();
            viewModel.onAddTodoButtonClick(todo);
        });
        return binding.getRoot();
    }

}
