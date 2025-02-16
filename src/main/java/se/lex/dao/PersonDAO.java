package se.lex.dao;

import se.lex.model.Person;

import java.util.Collection;
import java.util.List;

public interface PersonDAO {
    Person persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    Collection<Person> findAll();
    void remove(int id);
}
