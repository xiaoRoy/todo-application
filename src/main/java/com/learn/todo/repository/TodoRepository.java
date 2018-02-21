package com.learn.todo.repository;

import com.learn.todo.model.TodoItem;

import java.util.List;

public interface TodoRepository {

    List<TodoItem> findAll();

    TodoItem findById(long id);

    long insert(TodoItem todoItem);

    void update(TodoItem todoItem);

    void delete(TodoItem todoItem);
}
