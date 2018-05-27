package com.example.ike.todoapp.data.api.response;

public class GetTodosResponse {

    public Todo[] Items;

    public class Todo {
        public String date;
        public String title;
        public String content;
    }
}
