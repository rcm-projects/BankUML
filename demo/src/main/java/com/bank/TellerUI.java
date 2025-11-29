package com.bank;

import java.util.List;

public class TellerUI {

    private final Permissions tellerPerms;
    private final Search search;
    private final DataStore store;

    public TellerUI(Permissions tellerPerms, Search search, DataStore store) {
        this.tellerPerms = tellerPerms;
        this.search = search;
        this.store = store;
    }

    public List<Account> searchCustomerAccounts(String id,
                                                String name,
                                                String dob,
                                                String type) {
        return search.searchAccounts(store, id, name, dob, type, tellerPerms);
    }

    public void displayResults(List<Account> accounts) {
        System.out.println("Search results:");
        for (Account a : accounts) {
            System.out.println("- " + a.getAccountNumber()
                    + " (" + a.getAccountType() + ") owner=" + a.getCustomer().getName()
                    + " balance=" + a.getBalance());
        }
    }

     public Permissions getPermissions() {
        return tellerPerms;
    }

}
