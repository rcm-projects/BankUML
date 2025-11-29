package com.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accounts {

    private final List<User> users;
    private final List<Account> allAccounts;

    public Accounts() {
        this.users = new ArrayList<>();
        this.allAccounts = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user != null && !users.contains(user)) {
            users.add(user);
            allAccounts.addAll(user.getAccounts());
        }
    }

    public void registerAccount(User owner, Account account) {
        if (owner != null && account != null) {
            owner.addAccount(account);
            if (!allAccounts.contains(account)) {
                allAccounts.add(account);
            }
        }
    }


    public void removeUser(User user) {
        users.remove(user);
    }



    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<Account> getAllAccounts() {
        return Collections.unmodifiableList(allAccounts);
    }
}
