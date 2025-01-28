package se.lex.model;

import java.time.LocalDate;
import java.util.UUID;

public class TodoItem {
    private final UUID todoId; // Auto-generated UUID
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean isDone;
    private Person person;

    public TodoItem(String title, String description, LocalDate deadline, boolean isDone, Person person) {
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null");
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        if (deadline.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Deadline cannot be in the past");

        this.todoId = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.person = person;

        // Automatically add this TodoItem to the person's list
        person.addTodoItem(this);
    }

    public UUID getTodoId() {
        return todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", isDone=" + isDone +
                ", assignedTo='" + (person != null ? person.getName() : "None") + '\'' + // Show only the name
                '}';
    }
}