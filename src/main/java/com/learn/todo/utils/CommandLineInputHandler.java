package com.learn.todo.utils;

import com.learn.todo.model.TodoItem;
import com.learn.todo.repository.InMemoryTodoRepository;
import com.learn.todo.repository.TodoRepository;

import java.util.Collection;
import java.util.Scanner;

public class CommandLineInputHandler {
    private TodoRepository todoRepository = new InMemoryTodoRepository();

    public void printOptions() {
        System.out.println("\n--- To Do Application ---");
        System.out.println("Please make a choice:");
        System.out.println("(a)ll items");
        System.out.println("(f)ind a specific item");
        System.out.println("(i)nsert a new item");
        System.out.println("(u)pdate an existing item");
        System.out.println("(d)elete an existing item");
        System.out.println("(e)xit");
    }

    public String readInput() {
/*
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
*/
        return System.console().readLine("> ");
    }

    public void processInput(CommandLineInput input) {
        if (input == null) {
            handleUnknownInput();
        } else {
            switch (input) {
                case FIND_ALL:
                    printAllToDoItems();
                    break;
                case FIND_BY_ID:
                    printToDoItem();
                    break;
                case INSERT:
                    insertToDoItem();
                    break;
                case UPDATE:
                    updateToDoItem();
                    break;
                case DELETE:
                    deleteToDoItem();
                    break;
                case EXIT:
                    break;
                default:
                    handleUnknownInput();
            }
        }
    }

    private Long askForItemId() {
        System.out.println("Please enter the item ID:");
        String input = readInput();
        return Long.parseLong(input);
    }

    private TodoItem askForNewToDoAction() {
        TodoItem TodoItem = new TodoItem();
        System.out.println("Please enter the name of the item:");
        TodoItem.setName(readInput());
        return TodoItem;
    }

    private void printAllToDoItems() {
        Collection<TodoItem> toDoItems = todoRepository.findAll();

        if (toDoItems.isEmpty()) {
            System.out.println("Nothing to do. Go relax!");
        } else {
            for (TodoItem TodoItem : toDoItems) {
                System.out.println(TodoItem);
            }
        }
    }

    private void printToDoItem() {
        TodoItem TodoItem = findToDoItem();

        if (TodoItem != null) {
            System.out.println(TodoItem);
        }
    }

    private TodoItem findToDoItem() {
        Long id = askForItemId();
        TodoItem TodoItem = todoRepository.findById(id);

        if (TodoItem == null) {
            System.err.println("To do item with ID " + id + " could not be found.");
        }

        return TodoItem;
    }

    private void insertToDoItem() {
        TodoItem TodoItem = askForNewToDoAction();
        Long id = todoRepository.insert(TodoItem);
        System.out.println("Successfully inserted to do item with ID " + id + ".");
    }

    private void updateToDoItem() {
        TodoItem TodoItem = findToDoItem();

        if (TodoItem != null) {
            System.out.println(TodoItem);
            System.out.println("Please enter the name of the item:");
            TodoItem.setName(readInput());
            System.out.println("Please enter the done status the item:");
            TodoItem.setCompleted(Boolean.parseBoolean(readInput()));
            todoRepository.update(TodoItem);
            System.out.println("Successfully updated to do item with ID " + TodoItem.getId() + ".");
        }
    }

    private void deleteToDoItem() {
        TodoItem TodoItem = findToDoItem();

        if (TodoItem != null) {
            todoRepository.delete(TodoItem);
            System.out.println("Successfully deleted to do item with ID " + TodoItem.getId() + ".");
        }
    }

    private void handleUnknownInput() {
        System.out.println("Please select a valid option!");
    }
}
