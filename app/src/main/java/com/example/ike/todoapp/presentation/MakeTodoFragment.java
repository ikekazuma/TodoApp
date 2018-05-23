package com.example.ike.todoapp.presentation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ike.todoapp.databinding.FragmentMakeTodoBinding;

import dagger.android.support.DaggerFragment;

public class MakeTodoFragment extends DaggerFragment {

    FragmentMakeTodoBinding binding;

    public MakeTodoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMakeTodoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
