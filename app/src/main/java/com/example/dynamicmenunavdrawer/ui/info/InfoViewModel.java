package com.example.dynamicmenunavdrawer.ui.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dynamicmenunavdrawer.TODORepository;

import java.util.List;

public class InfoViewModel extends ViewModel {
    private TODORepository repository;

    public InfoViewModel() {
        repository = TODORepository.getInstance();
    }

    public LiveData<List<String>> getTodoItems() {
        return repository.getSelectedTodoItems();
    }

}