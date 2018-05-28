package com.example.ike.todoapp.data.api;

import com.example.ike.todoapp.data.api.request.AddTodosRequest;
import com.example.ike.todoapp.data.api.request.DeleteTodosRequest;
import com.example.ike.todoapp.data.api.request.GetTodosRequest;
import com.example.ike.todoapp.data.api.response.GetTodosResponse;
import com.example.ike.todoapp.data.api.response.UserResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface TodoAPI {

    @POST("user")
    Flowable<UserResponse> postUser();

    @POST("todos/all")
    Flowable<GetTodosResponse> getTodos(@Body GetTodosRequest request);

    @POST("todos")
    Flowable<Response<Void>> addTodos(@Body AddTodosRequest request);

    @HTTP(method = "DELETE", path = "todos", hasBody = true)
    Flowable<Response<Void>> deleteTodos(@Body DeleteTodosRequest request);
}
