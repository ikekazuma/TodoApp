package com.example.ike.todoapp.data.api.request;

import com.example.ike.todoapp.model.Todo;

public class UpdateTodosRequest {
    public String token;
    public Todo todo;
    public UpdateTodosRequest(String token, Todo todo) {
        this.token = token;
        this.todo = todo;
    }
}
