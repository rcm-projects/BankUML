package com.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final String id;
    private String username;
    private String password;
    private String birthPlace;
    private String dob; // yyyy-mm-dd
    private final List<Account> accounts;
    private Permissions perms;

    public User(String id,
                String username,
                String password,
                String birthPlace,
                String dob,
                Permissions perms) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.birthPlace = birthPlace;
        this.dob = dob;
        this.perms = perms;
        this.accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDob() {
        return dob;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Permissions getPerms() {
        return perms;
    }

    public void setPerms(Permissions perms) {
        this.perms = perms;
    }

    public void addAccount(Account account) {
        if (account != null)
            accounts.add(account);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

}
