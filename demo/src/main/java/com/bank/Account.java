package com.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base for all account types (Card, Check, Saving).
 * Holds balance and transaction history.
 */

public abstract class Account {

    protected Customer customer;
    protected List<Transaction> transactions;

    // Design attributes from document
    protected String accountNumber;
    protected String accountType;
    protected double balance;

    public Account(Customer customer) {
        this(customer, null, null, 0.0);
    }

    public Account(Customer customer, String accountNumber, String accountType, double openingBalance) {
        this.customer = customer;
        this.transactions = new ArrayList<>();
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = openingBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction t) {
        if (t != null) {
            transactions.add(t);
        }
    }

    /**
     * Used from Transactions service to update balance.
     */
    protected void applyAmount(double delta) {
        this.balance += delta;
    }

    public abstract void pay();
    public abstract void receipt();
}
