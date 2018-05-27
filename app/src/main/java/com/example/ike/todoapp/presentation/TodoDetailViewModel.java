package com.example.ike.todoapp.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.ike.todoapp.data.repository.TodoRepository;
import com.example.ike.todoapp.data.repository.UserRepository;
import com.example.ike.todoapp.model.Todo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodoDetailViewModel extends ViewModel {

    private UserRepository userRepository;

    private TodoRepository todoRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Result> delete = new MutableLiveData<>();

    public LiveData<Boolean> isDeleting = Transformations.map(delete, result -> result != null && result.isLoading());

    @Inject
    public TodoDetailViewModel (UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public void onDeleteButtonClick(Todo todo) {
        compositeDisposable.add(deleteTodo(todo));
    }

    private Disposable deleteTodo(Todo todo) {
        return userRepository.getUserToken()
                .flatMap(token -> todoRepository.deleteTodo(token, todo))
                .subscribeOn(Schedulers.io())
                .map(Result::success)
                .startWith(Result.loading())
                .onErrorReturn(Result::failure)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            delete.postValue(result);
                        }
                );
    }
}
