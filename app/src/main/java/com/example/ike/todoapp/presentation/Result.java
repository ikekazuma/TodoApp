package com.example.ike.todoapp.presentation;

/**
 * Created by ike on 2018/03/11.
 */

public class Result {

    private boolean inProgress = false;

    private Object data = null;

    private Throwable error = null;

    public Result(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(Throwable error) {
        this.error = error;
    }

    public static Result loading() {
        return new Result(true);
    }

    public static Result success(Object model) {
        return new Result(model);
    }

    public static Result failure(Throwable error) {
        return new Result(error);
    }

    public boolean isLoading() {
        return inProgress;
    }

    public Object data() {
        return data;
    }

    public Throwable throwable() {
        return error;
    }
}
