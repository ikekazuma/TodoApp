package com.example.ike.todoapp.data.repository;

import com.example.ike.todoapp.model.Todo;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class TodoRepositoryImpl implements TodoRepository {

    // dummy
    private static final Todo[] mock = new Todo[] {
            new Todo(1, "title1", "content1"),
            new Todo(2, "title2", "content2"),
            new Todo(3, "title3", "content3"),
            new Todo(4, "title4", "content4"),
            new Todo(5, "title5", "content5"),
    };

    @Inject
    public TodoRepositoryImpl() {

    }

    @Override
    public void addTodo(Todo todo) {

    }

    @Override
    public Flowable<List<Todo>> getTodo()  {
        return Flowable.create(
                subscriber -> {
                    Thread.sleep(2000);
                    subscriber.onNext(Arrays.asList(mock));
                    subscriber.onComplete();
                }
                , BackpressureStrategy.DROP);
    }
}
