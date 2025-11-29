package com.bank;

import java.util.ArrayList;
import java.util.List;

public class AdminUI {

    private final Permissions adminPerms;
    private final Search search;
    private final DataStore store;

    public AdminUI(Permissions adminPerms, Search search, DataStore store) {
        this.adminPerms = adminPerms;
        this.search = search;
        this.store = store;
    }

    public List<Account> getAllAccounts() {
        // Admin can call search with all filters null
        return new ArrayList<>(search.searchAccounts(store, null, null, null, null, adminPerms));
    }

    public void printAllAccounts() {
        List<Account> accounts = getAllAccounts();
        System.out.println("All accounts in system:");
        for (Account a : accounts) {
            System.out.println("- " + a.getAccountNumber()
                    + " (" + a.getAccountType() + ") owner=" + a.getCustomer().getName()
                    + " balance=" + a.getBalance());
        }
    }
}
