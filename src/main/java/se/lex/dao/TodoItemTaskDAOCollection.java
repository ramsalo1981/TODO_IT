package se.lex.dao;

import se.lex.model.TodoItemTask;

import java.util.*;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{
    private static final TodoItemTaskDAOCollection INSTANCE = new TodoItemTaskDAOCollection();
    private final Map<Integer, TodoItemTask> todoItemTasks = new HashMap<>();

    private TodoItemTaskDAOCollection() {}

    public static TodoItemTaskDAOCollection getInstance() {
        return INSTANCE;
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        todoItemTasks.put(todoItemTask.getId(), todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        return todoItemTasks.get(id);
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return todoItemTasks.values();
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean assigned) {
        return todoItemTasks.values().stream()
                .filter(task -> task.isAssigned() == assigned)
                .toList();
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return todoItemTasks.values().stream()
                .filter(task -> task.getAssignee().getId() == personId)
                .toList();
    }

    @Override
    public void remove(int id) {
        todoItemTasks.remove(id);
    }
}
