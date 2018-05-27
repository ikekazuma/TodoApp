package com.example.ike.todoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {

    public String title;
    public String content;
    public String date;

    public Todo() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.date);
    }

    protected Todo(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}
