package se.lex.dao;

import se.lex.model.TodoItem;

import java.time.LocalDate;
import java.util.*;

public class TodoItemDAOCollection implements TodoItemDAO{
    private static final TodoItemDAOCollection INSTANCE = new TodoItemDAOCollection();
    private final Map<Integer, TodoItem> todoItems = new HashMap<>();

    private TodoItemDAOCollection() {}

    public static TodoItemDAOCollection getInstance() {
        return INSTANCE;
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        todoItems.put(todoItem.getId(), todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        return todoItems.get(id);
    }

    @Override
    public Collection<TodoItem> findAll() {
        return todoItems.values();
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return todoItems.values().stream()
                .filter(todoItem -> todoItem.isDone() == done)
                .toList();
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return todoItems.values().stream()
                .filter(todoItem -> todoItem.getTitle().contains(title))
                .toList();
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return todoItems.values().stream()
                .filter(todoItem -> todoItem.getCreator().getId() == personId)
                .toList();
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return todoItems.values().stream()
                .filter(todoItem -> todoItem.getDeadLine().isBefore(date))
                .toList();
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return todoItems.values().stream()
                .filter(todoItem -> todoItem.getDeadLine().isAfter(date))
                .toList();
    }

    @Override
    public void remove(int id) {
        todoItems.remove(id);
    }
}
