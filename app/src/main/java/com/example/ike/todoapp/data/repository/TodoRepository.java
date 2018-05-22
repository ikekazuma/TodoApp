package com.example.ike.todoapp.data.repository;

import com.example.ike.todoapp.model.Todo;

import java.util.List;

import io.reactivex.Flowable;

public interface TodoRepository {
    void addTodo(Todo todo);
    Flowable<List<Todo>> getTodo();
}