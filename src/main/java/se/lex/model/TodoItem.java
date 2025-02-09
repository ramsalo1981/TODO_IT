package se.lex.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoItem {
    private static final AtomicInteger count = new AtomicInteger(0); // Thread-safe ID generation
    private final int todoItemId;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean isDone;
    private Person assignee;

    public TodoItem( String title, String description, LocalDate deadline, Person assignee) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (deadline.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Deadline cannot be in the past");
        if (assignee == null)
            throw new IllegalArgumentException("Assignee cannot be null");

        this.todoItemId = count.incrementAndGet();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignee = assignee;
    }

    public int getTodoItemId() {
        return todoItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Deadline cannot be in the past");
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return todoItemId == todoItem.todoItemId &&
                Objects.equals(title, todoItem.title) &&
                Objects.equals(deadline, todoItem.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoItemId, title, deadline);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoItemId=" + todoItemId +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", assignee=" + assignee.getFirstName() + " " + assignee.getLastName() +
                '}';
    }
}