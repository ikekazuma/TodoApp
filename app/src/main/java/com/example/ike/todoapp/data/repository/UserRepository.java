package com.example.ike.todoapp.data.repository;

import io.reactivex.Flowable;

public interface UserRepository {
    Flowable<String> getUserToken();
}
