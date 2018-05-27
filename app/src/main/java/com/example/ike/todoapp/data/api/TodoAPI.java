package com.example.ike.todoapp.data.api;

import com.example.ike.todoapp.data.api.response.GetTodosResponse;
import com.example.ike.todoapp.data.api.response.UserResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TodoAPI {

    @POST("user")
    Flowable<UserResponse> postUser();

    @GET("todos")
    Flowable<GetTodosResponse> getTodos(@Query("token") String token);

    @POST("todos")
    Flowable<Response<Void>> addTodos(@Query("token") String token, @Query("date") String date, @Query(value = "title", encoded = true) String title, @Query(value = "content", encoded = true) String content);

    @DELETE("todos")
    Flowable<Response<Void>> deleteTodos(@Query("token") String token, @Query("date") String date);
}
