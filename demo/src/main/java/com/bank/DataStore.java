package com.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test-only storage used for bottom-up integration tests.
 * (As described in the Integration Testing section of the report.)
 * 
 * Real system uses a centralized external database.
 */
public class DataStore {

    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    public void saveTransaction(Transaction transaction) {
        addTransaction(transaction);
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
        }
    }
}
