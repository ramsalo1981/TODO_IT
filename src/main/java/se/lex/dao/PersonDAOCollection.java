package se.lex.dao;

import se.lex.model.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonDAOCollection implements PersonDAO{
    private static final PersonDAOCollection INSTANCE = new PersonDAOCollection();
    private final Map<Integer, Person> persons = new HashMap<>();

    private PersonDAOCollection() {}

    public static PersonDAOCollection getInstance() {
        return INSTANCE;
    }

    @Override
    public Person persist(Person person) {
        persons.put(person.getId(), person);
        System.out.println("Person added: " + person);
        return person;
    }

    @Override
    public Person findById(int id) {
        return persons.get(id);
    }

    @Override
    public Person findByEmail(String email) {
        return persons.values().stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Person> findAll() {
        return persons.values();
    }

    @Override
    public void remove(int id) {
        persons.remove(id);
    }
}
