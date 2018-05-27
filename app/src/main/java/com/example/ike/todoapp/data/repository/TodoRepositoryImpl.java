package com.example.ike.todoapp.data.repository;

import com.example.ike.todoapp.data.api.TodoAPI;
import com.example.ike.todoapp.data.api.response.GetTodosResponse;
import com.example.ike.todoapp.model.Todo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class TodoRepositoryImpl implements TodoRepository {

    private TodoAPI api;

    @Inject
    public TodoRepositoryImpl(TodoAPI api) {
        this.api = api;
    }

    @Override
    public Flowable<String> addTodo(String token, Todo todo) {
        return api.addTodos(token, todo.date, todo.title, todo.content).map(response -> {
            if (response.isSuccessful()) {
                return "success";
            }
            return null;
        } );
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
