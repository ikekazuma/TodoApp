package com.example.ike.todoapp.data.api.request;

import com.example.ike.todoapp.model.Todo;

public class DeleteTodosRequest {
    public String token;
    public Todo todo;
    public DeleteTodosRequest(String token, Todo todo) {
        this.token = token;
        this.todo = todo;
    }
}
