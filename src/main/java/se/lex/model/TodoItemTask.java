package se.lex.model;

import java.util.UUID;

public class TodoItemTask {
    private final UUID taskId; // Auto-generated UUID
    private TodoItem todoItem;
    private boolean isAssigned;
    private Person assignedPerson;

    public TodoItemTask(TodoItem todoItem, boolean isAssigned, Person assignedPerson) {
        if (todoItem == null)
            throw new IllegalArgumentException("TodoItem cannot be null");
        this.taskId = UUID.randomUUID();
        this.todoItem = todoItem;
        this.isAssigned = isAssigned;
        this.assignedPerson = assignedPerson;

        // If assigned, add this task to the assigned person's todo items
        if (isAssigned && assignedPerson != null) {
            assignedPerson.addTodoItem(todoItem);
        }
    }

    public UUID getTaskId() {
        return taskId;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public Person getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(Person assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    @Override
    public String toString() {
        return "TodoItemTask{" +
                "taskId=" + taskId +
                ", todoItem=" + todoItem +
                ", isAssigned=" + isAssigned +
                ", assignedPerson=" + assignedPerson +
                '}';
    }
}
