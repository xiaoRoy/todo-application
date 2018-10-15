package com.learn.todo.repository;

import com.learn.todo.model.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTodoRepository implements TodoRepository{

    private AtomicLong currentId = new AtomicLong();

    private ConcurrentMap<Long, TodoItem> todos = new ConcurrentHashMap<>();

    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoItems = new ArrayList<>(todos.values());
        Collections.sort(todoItems);
        return todoItems;
    }

    @Override
    public TodoItem findById(long id) {
        return todos.get(id);
    }

    @Override
    public long insert(TodoItem todoItem) {
        Long id = currentId.getAndIncrement();
        todoItem.setId(id);
        todos.putIfAbsent(id, todoItem);
        return id;
    }

    @Override
    public void update(TodoItem todoItem) {
        todos.replace(todoItem.getId(), todoItem);
    }

    @Override
    public void delete(TodoItem todoItem) {
        todos.remove(todoItem.getId());
    }
}
