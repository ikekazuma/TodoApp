package com.example.ike.todoapp.data.api;

import com.example.ike.todoapp.data.api.response.GetTodosResponse;
import com.example.ike.todoapp.data.api.response.UserResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TodoAPI {

    @POST("todos/user")
    Flowable<UserResponse> postUser();

    @GET("todos")
    Flowable<GetTodosResponse> getTodos(@Query("token") String token);

    @POST("todos/add")
    Flowable<Response<Void>> addTodos(@Query("token") String token, @Query("date") String date, @Query("title") String title, @Query("content") String content);
}
