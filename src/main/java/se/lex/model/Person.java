package se.lex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {
    private final UUID personId;
    private String name;
    private String email;
    private List<TodoItem> todoItems;

    public Person(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (!email.contains("@") || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.personId = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.todoItems = new ArrayList<>();
    }

    public UUID getPersonId() {
        return personId;
    }



    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }



    public List<TodoItem> getTodoItems() {
        return todoItems;
    }



    public void addTodoItem(TodoItem todoItem) {
        if (todoItem != null && !todoItems.contains(todoItem)) {
            todoItems.add(todoItem);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", todoItems=" + todoItems.size() +
                '}';
    }
}
