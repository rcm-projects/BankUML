package com.bank;

import java.util.List;

public class CustomerUI {

    private final User customer;

    public CustomerUI(User customer) {
        this.customer = customer;
    }

    public void displayAccountList() {
        System.out.println("Accounts for customer: " + customer.getUsername());
        for (Account a : customer.getAccounts()) {
            System.out.println("- " + a.getAccountNumber()
                    + " (" + a.getAccountType() + ") balance=" + a.getBalance());
        }
    }

    public void displayAccountDetails(Account account) {
        if (!AuthService.canViewAccount(customer, account)) {
            throw new SecurityException("Cannot view this account.");
        }

        System.out.println("Account overview for " + account.getAccountNumber());
        System.out.println("Type: " + account.getAccountType());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Transactions:");
        List<Transaction> txs = account.getTransactions();
        for (Transaction t : txs) {
            System.out.println("  - " + t.getTimestamp()
                    + " " + t.getType()
                    + " " + t.getAmount()
                    + " (" + t.getDescription() + ")");
        }
    }
}
