package com.example.dynamicmenunavdrawer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TODORepository {
    public static final int TODO_LIST_COUNT = 15;
    public static final int TODO_COUNT = 20;

    private static TODORepository instance;
    private Map<String, List<String>> todos;
    private MutableLiveData<List<String>> todoNames;
    private MutableLiveData<List<String>> selectedTodoListItems;


    public static TODORepository getInstance() {
        if (instance == null) {
            instance = new TODORepository();
        }
        return instance;
    }

    private TODORepository() {
        todoNames = new MutableLiveData<>();
        selectedTodoListItems = new MutableLiveData<>();
        generateData();
    }

    public void setSelectedTodoList(String selectedTodoList) {
        List<String> items = todos.get(selectedTodoList);
        selectedTodoListItems.setValue(items);
    }

    public LiveData<List<String>> getSelectedTodoItems() {
        if (selectedTodoListItems != null) {
            return selectedTodoListItems;
        }
        return null;
    }

    public LiveData<List<String>> getTodoListsNames() {
        return todoNames;
    }

    public void addTODOList() {
        long key = GregorianCalendar.getInstance().getTimeInMillis();
        String todoKey = "TODO " + key;
        todos.put(todoKey, genereteTodoItems(todoKey));
        extractTodoNamesToLiveData();
    }

    private void generateData() {
        todos = new HashMap<>();
        for (int i = 0; i < TODO_LIST_COUNT; i++) {
            String todoKey = "TODO " + i;
            todos.put(todoKey, genereteTodoItems(todoKey));
        }
        extractTodoNamesToLiveData();
    }

    private List<String> genereteTodoItems(String todoKey) {
        List<String> todoList = new ArrayList<>();
        for (int j = 0; j < TODO_COUNT; j++) {
            todoList.add(todoKey + " item " + j);
        }
        return todoList;
    }

    private void extractTodoNamesToLiveData() {
        List<String> names = new ArrayList<>(todos.keySet());
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        todoNames.setValue(names);
    }
}
