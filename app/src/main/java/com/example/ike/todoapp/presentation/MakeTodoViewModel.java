package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.ike.todoapp.data.repository.TodoRepository;
import com.example.ike.todoapp.data.repository.UserRepository;
import com.example.ike.todoapp.model.Todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MakeTodoViewModel extends ViewModel {

    private UserRepository userRepository;

    private TodoRepository todoRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Result> add = new MutableLiveData<>();

    public LiveData<Boolean> isAdding = Transformations.map(add, result -> result != null && result.isLoading());

    public MutableLiveData<Result> update = new MutableLiveData<>();

    public LiveData<Boolean> isUpdating = Transformations.map(update, result -> result != null && result.isLoading());

    // Todoを追加したかどうかのフラグ
    public boolean isAdded = false;

    @Inject
    public MakeTodoViewModel(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public void onAddTodoButtonClick(Todo todo) {
        todo.date = getDate();
        compositeDisposable.add(addTodo(todo));
    }

    private Disposable addTodo(Todo todo) {
        return userRepository.getUserToken()
                .flatMap(token -> todoRepository.addTodo(token, todo))
                .subscribeOn(Schedulers.io())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            if (result.data() != null) {
                                isAdded = true;
                            }
                            add.postValue(result);
                        }
                );
    }

    public void onUpdateButtonClick(Todo todo) {
        compositeDisposable.add(updateTodo(todo));
    }

    private Disposable updateTodo(Todo todo) {
        return userRepository.getUserToken()
                .flatMap(token -> todoRepository.updateTodo(token, todo))
                .subscribeOn(Schedulers.io())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            update.postValue(result);
                        }
                );
    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        final Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
