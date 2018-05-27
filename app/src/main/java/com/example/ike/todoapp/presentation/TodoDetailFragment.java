package com.example.ike.todoapp.presentation;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ike.todoapp.databinding.FragmentTodoDetailBinding;
import com.example.ike.todoapp.di.ViewModelFactory;
import com.example.ike.todoapp.model.Todo;

import javax.inject.Inject;

public class TodoDetailFragment extends Fragment {

    FragmentTodoDetailBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    TodoDetailViewModel viewModel;

    public TodoDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTodoDetailBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(TodoDetailViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        Todo todo = getArguments().getParcelable("todo");
        if (todo != null) {
            binding.setTodo(todo);
        }
        return binding.getRoot();
    }


    public static TodoDetailFragment newInstance(Todo todo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("todo", todo);
        TodoDetailFragment fragment = new TodoDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
