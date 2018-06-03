package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.ike.todoapp.data.repository.TodoRepository;
import com.example.ike.todoapp.data.repository.UserRepository;
import com.example.ike.todoapp.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ike on 2018/05/22.
 */

public class TodoListViewModel extends ViewModel {

    private UserRepository userRepository;

    private TodoRepository todoRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Result> todo = new MutableLiveData<>();

    public LiveData<Boolean> isLoading = Transformations.map(todo, todo -> todo != null && todo.isLoading());

    public MutableLiveData<Result> delete = new MutableLiveData<>();

    public LiveData<Boolean> isDeleting = Transformations.map(delete, result -> result != null && result.isLoading());

    @Inject
    public TodoListViewModel(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        load();
    }

    public void loadTodo() {
        compositeDisposable.add(load());
    }

    private Disposable load() {
        return userRepository.getUserToken()
                .flatMap(token -> todoRepository.getTodos(token))
                .subscribeOn(Schedulers.io())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> todo.postValue(result)
                );
    }

    public void onSwiped(int position){
        if (todo.getValue() == null || todo.getValue().data() == null ||  ((List<Todo>)todo.getValue().data()).size() < position) {
            return;
        }
        Todo item = ((List<Todo>) todo.getValue().data()).get(position);
        compositeDisposable.add(delete(item));
    }

    private Disposable delete(Todo todo) {
        return userRepository.getUserToken()
                .flatMap(token -> todoRepository.deleteTodo(token, todo))
                .subscribeOn(Schedulers.io())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> delete.postValue(result)
                );
    }
}
