package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.ike.todoapp.data.repository.TodoRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ike on 2018/05/22.
 */

public class TodoListViewModel extends ViewModel {

    private TodoRepository todoRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Result> todo = new MutableLiveData<>();

    public LiveData<Boolean> isLoading = Transformations.map(todo, todo -> todo != null && todo.isLoading());

    @Inject
    public TodoListViewModel(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void loadTodo() {
        compositeDisposable.add(load());
    }

    private Disposable load() {
        return todoRepository.getTodo()
                .subscribeOn(Schedulers.newThread())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> todo.postValue(result)
                );
    }
}
