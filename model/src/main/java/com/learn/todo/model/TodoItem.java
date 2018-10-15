package com.learn.todo.model;

public class TodoItem implements Comparable<TodoItem>{

    private long id;

    private String name;

    private boolean completed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || getClass() != another.getClass()) return false;

        TodoItem todoItem = (TodoItem) another;

        return id == todoItem.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int compareTo(TodoItem another) {
        return ((int) (another.id - this.id));
    }
}
