package com.example.ike.todoapp.data.repository;

import com.example.ike.todoapp.data.api.TodoAPI;
import com.example.ike.todoapp.data.api.response.GetTodosResponse;
import com.example.ike.todoapp.model.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class TodoRepositoryImpl implements TodoRepository {

    private TodoAPI api;

    @Inject
    public TodoRepositoryImpl(TodoAPI api) {
        this.api = api;
    }

    @Override
    public void addTodo(Todo todo) {

    }

    @Override
    public Flowable<List<Todo>> getTodos(String token) {
        return api.getTodos(token).map(getTodosResponse -> {
            List<Todo> todos = new ArrayList<>();
            for (GetTodosResponse.Todo resTodo : getTodosResponse.Items) {
                Todo todo = new Todo();
                todo.title = resTodo.title;
                todo.content = resTodo.content;
                todo.date = resTodo.date;
                todos.add(todo);
            }
            return todos;
        });
    }
}
