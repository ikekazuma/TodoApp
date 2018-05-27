package com.example.ike.todoapp.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ike.todoapp.R;
import com.example.ike.todoapp.data.api.TodoAPI;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class UserRepositoryImpl implements UserRepository {

    private Context context;

    private TodoAPI api;

    @Inject
    public UserRepositoryImpl(Context context, TodoAPI api) {
        this.context = context;
        this.api = api;
    }

    @Override
    public Flowable<String> getUserToken() {
        // tokenがローカルにない場合はAPIで生成して返却する
        String savedToken = token();
        if (savedToken == null || savedToken.isEmpty()) {
            return api.postUser().map(userResponse -> {
                String token = userResponse.user_token;
                setToken(token);
                return token;
            });
        }
        return Flowable.just(savedToken);
    }

    private String token() {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        if (preferences != null) {
            String key = context.getString(R.string.shared_preferences_key_token);
            return preferences.getString(key, null);
        }
        return null;
    }

    private void setToken(String token) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            String key = context.getString(R.string.shared_preferences_key_token);
            editor.putString(key, token);
            editor.apply();
        }
    }


}
