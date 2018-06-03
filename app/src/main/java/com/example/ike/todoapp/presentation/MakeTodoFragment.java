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
        Todo t = getArguments().getParcelable("todo");
        if (t != null) {
            binding.title.setText(t.title);
            binding.content.setText(t.content);
            binding.button.setOnClickListener(view -> {
                if (binding.title.getText().toString().isEmpty() || binding.content.getText().toString().isEmpty()) {
                    return;
                }
                t.title = binding.title.getText().toString();
                t.content = binding.content.getText().toString();
                viewModel.onUpdateButtonClick(t);
            });
        } else {
            binding.button.setOnClickListener(view -> {
                if (binding.title.getText().toString().isEmpty() || binding.content.getText().toString().isEmpty()) {
                    return;
                }
                Todo todo = new Todo();
                todo.title = binding.title.getText().toString();
                todo.content = binding.content.getText().toString();
                viewModel.onAddTodoButtonClick(todo);
            });
        }

        return binding.getRoot();
    }

    public static MakeTodoFragment newInstance(Todo todo) {
        Bundle bundle = new Bundle();
        MakeTodoFragment fragment = new MakeTodoFragment();
        if (todo != null) {
            bundle.putParcelable("todo", todo);
        }
        fragment.setArguments(bundle);
        return fragment;
    }
}
