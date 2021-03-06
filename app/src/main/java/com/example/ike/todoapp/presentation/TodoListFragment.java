package com.example.ike.todoapp.presentation;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        final RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new TodoListAdapter(new ArrayList<>()) {
            @Override
            protected void onClick(Todo todo) {
                try {
                    OnItemClickListener listener = (OnItemClickListener) getActivity();
                    listener.onItemClick(todo);
                } catch (ClassCastException e) {
                    throw new ClassCastException(getActivity().toString()
                            + " must implement OnItemClickListener");
                }
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                adapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                viewModel.onSwiped(fromPos);
                adapter.deleteData(fromPos);
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        viewModel.todo.observe(this, result -> {
            if (result != null && result.data() != null) {
                setRecyclerView((List<Todo>)result.data());
            }
        });
        viewModel.delete.observe(this, result -> {
            if (result != null && result.data() != null) {
                viewModel.loadTodo();
            }
        });
        return binding.getRoot();
    }

    private void setRecyclerView(List<Todo> todos) {
        if (todos == null) {
            return;
        }
        adapter.setData(todos);
        binding.recyclerView.setVisibility(todos.size() == 0 ? View.GONE : View.VISIBLE);
        binding.emptyView.setVisibility(todos.size() == 0 ? View.VISIBLE : View.GONE);
    }

    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }
}
