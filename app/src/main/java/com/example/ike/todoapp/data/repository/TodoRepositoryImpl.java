package com.example.ike.todoapp.data.repository;

import com.example.ike.todoapp.data.api.TodoAPI;
import com.example.ike.todoapp.data.api.request.AddTodosRequest;
import com.example.ike.todoapp.data.api.request.DeleteTodosRequest;
import com.example.ike.todoapp.data.api.request.GetTodosRequest;
import com.example.ike.todoapp.data.api.request.UpdateTodosRequest;
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
        return api.addTodos(new AddTodosRequest(token, todo)).map(response -> {
            if (response.isSuccessful()) {
                return "success";
            }
            return null;
        } );
    }

    @Override
    public Flowable<List<Todo>> getTodos(String token) {
        return api.getTodos(new GetTodosRequest(token)).map(getTodosResponse -> {
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

    @Override
    public Flowable<String> updateTodo(String token, Todo todo) {
        return api.updateTodos(new UpdateTodosRequest(token, todo)).map(response -> {
            if (response.isSuccessful()) {
                return "success";
            }
            return null;
        } );
    }

    @Override
    public Flowable<String> deleteTodo(String token, Todo todo) {
        return api.deleteTodos(new DeleteTodosRequest(token, todo)).map(response -> {
            if (response.isSuccessful()) {
                return "success";
            }
            return null;
        } );
    }
}
