package com.example.ike.todoapp.presentation;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.databinding.TodoItemBinding;
import com.example.ike.todoapp.model.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private List<Todo> data;

    protected void onClick(Todo todo) { }

    public TodoListAdapter(List<Todo> data) {
        this.data = data;
    }

    public void setData(List<Todo> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    
    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TodoListAdapter.ViewHolder holder, int position) {
        Todo todo = data.get(position);
        holder.binding.title.setText(todo.title);
        holder.binding.title.setOnClickListener((view -> onClick(todo)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TodoItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
