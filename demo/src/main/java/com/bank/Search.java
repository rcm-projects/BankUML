package com.bank;

import java.util.ArrayList;
import java.util.List;

public class Search {

    /**
     * Implements the "Filter accounts by attributes" use case.
     */
    public List<Account> searchAccounts(DataStore store,
                                        String accountId,
                                        String name,
                                        String dob,
                                        String accountType,
                                        Permissions requesterPerms) {

        if (!AuthService.canSearchAccounts(requesterPerms)) {
            throw new SecurityException("Insufficient permissions to search accounts.");
        }

        List<Account> results = new ArrayList<>();

        for (User user : store.getAllUsers()) {
            boolean matchUser =
                    (name == null || user.getUsername().equalsIgnoreCase(name))
                            && (dob == null || dob.equals(user.getDob()));

            if (!matchUser) {
                continue;
            }

            for (Account acc : user.getAccounts()) {
                boolean matchAccount =
                        (accountId == null || accountId.equals(acc.getAccountNumber()))
                                && (accountType == null
                                || (acc.getAccountType() != null
                                && acc.getAccountType().equalsIgnoreCase(accountType)));

                if (matchAccount) {
                    results.add(acc);
                }
            }
        }

        return results;
    }
}
