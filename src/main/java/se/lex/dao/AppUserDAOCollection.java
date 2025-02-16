package se.lex.dao;

import se.lex.model.AppUser;
import se.lex.sequencer.AppUserIdSequencer;

import java.util.*;

public class AppUserDAOCollection implements AppUserDAO  {
    private static final AppUserDAOCollection INSTANCE = new AppUserDAOCollection();
    private final Map<String, AppUser> appUsers = new HashMap<>();

    private AppUserDAOCollection() {}

    public static AppUserDAOCollection getInstance() {
        return INSTANCE;
    }

    @Override
    public AppUser persist(AppUser appUser) {
        appUsers.put(appUser.getUsername(), appUser);
        System.out.println("AppUser added: " + appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUsers.get(username);
    }

    @Override
    public Collection<AppUser> findAll() {
        return appUsers.values();
    }

    @Override
    public void remove(String username) {
        appUsers.remove(username);
    }
}
