package com.bank;

import java.time.LocalDateTime;

public class Transaction {

    public enum Type {
        DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT
    }

    private final Type type;
    private final double amount;
    private final String accountNumber;
    private final String description;
    private final LocalDateTime timestamp;

    public Transaction() {
        this(Type.PAYMENT, 0.0, null, "Generic transaction");
    }

    public Transaction(Type type, double amount, String accountNumber, String description) {
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public Type getType() { return type; }
    public double getAmount() { return amount; }
    public String getAccountNumber() { return accountNumber; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void pay() {
        System.out.println("Payment transaction completed.");
    }

    public void receipt() {
        System.out.println("Transaction receipt generated.");
    }
}
