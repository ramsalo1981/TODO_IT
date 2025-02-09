package se.lex.model;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoItemTask {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int taskId; // Use UUID for unique ID
    private TodoItem todoItem;
    private boolean isAssigned;
    private Person assignedPerson;

    public TodoItemTask(TodoItem todoItem, boolean isAssigned, Person assignedPerson) {
        if (todoItem == null)
            throw new IllegalArgumentException("TodoItem cannot be null");

        this.taskId = count.incrementAndGet();
        this.todoItem = todoItem;
        this.isAssigned = isAssigned;
        this.assignedPerson = assignedPerson;
    }

    public int getTaskId() {
        return taskId;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null)
            throw new IllegalArgumentException("TodoItem cannot be null");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
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
