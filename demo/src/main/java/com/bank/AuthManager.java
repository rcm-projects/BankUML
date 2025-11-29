package com.bank;

import java.util.Optional;

public class AuthManager {

    private final DataStore store;

    public AuthManager(DataStore store) {
        this.store = store;
    }

    public Optional<User> login(String username, String password) {
        return store.getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPerms().getPassword().equals(password))
                .findFirst();
    }
}
