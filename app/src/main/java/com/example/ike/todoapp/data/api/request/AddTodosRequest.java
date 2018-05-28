package com.example.ike.todoapp.data.api.request;

import com.example.ike.todoapp.model.Todo;

public class AddTodosRequest {
    public String token;
    public Todo todo;
    public AddTodosRequest(String token, Todo todo) {
        this.token = token;
        this.todo = todo;
    }
}
