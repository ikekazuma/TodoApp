package com.example.ike.todoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    public int id;
    public String title;
    public String content;

    public Todo(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    protected Todo(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
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
