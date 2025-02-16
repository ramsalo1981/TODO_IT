package se.lex.dao;

import se.lex.model.AppUser;

import java.util.Collection;
import java.util.List;

public interface AppUserDAO {
    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);
}
