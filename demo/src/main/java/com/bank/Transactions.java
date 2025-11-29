package com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transactions {

    private Transactions() {}

    private static double normalizeAmount(double amount) {
        BigDecimal bd = BigDecimal.valueOf(amount).setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    private static void validatePositive(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
    }

    public static Transaction deposit(Account account,
                                      double amount,
                                      String description,
                                      DataStore store) {

        double normalized = normalizeAmount(amount);
        validatePositive(normalized);

        account.applyAmount(normalized);
        Transaction tx = new Transaction(Transaction.Type.DEPOSIT,
                normalized,
                account.getAccountNumber(),
                description);
        account.addTransaction(tx);
        store.saveTransaction(tx);
        return tx;
    }

    public static Transaction withdraw(Account account,
                                       double amount,
                                       String description,
                                       DataStore store)
            throws InsufficientBalanceException {

        double normalized = normalizeAmount(amount);
        validatePositive(normalized);

        if (normalized > account.getBalance()) {
            throw new InsufficientBalanceException("Insufficient Balance.");
        }

        account.applyAmount(-normalized);
        Transaction tx = new Transaction(Transaction.Type.WITHDRAWAL,
                normalized,
                account.getAccountNumber(),
                description);
        account.addTransaction(tx);
        store.saveTransaction(tx);
        return tx;
    }
}
