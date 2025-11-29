package com.bank.ui;

import com.bank.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;

public class TransactionManagementUI extends VBox {

    public TransactionManagementUI(User user) {
        setPadding(new Insets(10));
        setSpacing(10);
        Label title = new Label("Transaction Management");

        ComboBox<Account> accountBox = new ComboBox<>();
        accountBox.setItems(FXCollections.observableArrayList(user.getAccounts()));
        accountBox.setPromptText("Select Account");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");

        Label status = new Label();

        depositBtn.setOnAction(e -> {
            Account acc = accountBox.getValue();
            if (acc == null) { status.setText("Select an account."); return; }
            try {
                double amt = Double.parseDouble(amountField.getText());
                Transaction tx = Transactions.deposit(acc, amt, "UI Deposit", DemoContext.getStore());
                status.setText(tx.getType() + " of " + tx.getAmount() + " completed. New balance: " + acc.getBalance());
            } catch (NumberFormatException ex) {
                status.setText("Invalid amount.");
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });

        withdrawBtn.setOnAction(e -> {
            Account acc = accountBox.getValue();
            if (acc == null) { status.setText("Select an account."); return; }
            try {
                double amt = Double.parseDouble(amountField.getText());
                Transaction tx = Transactions.withdraw(acc, amt, "UI Withdrawal", DemoContext.getStore());
                status.setText(tx.getType() + " of " + tx.getAmount() + " completed. New balance: " + acc.getBalance());
            } catch (NumberFormatException ex) {
                status.setText("Invalid amount.");
            } catch (InsufficientBalanceException ex) {
                status.setText(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });

        getChildren().addAll(title, accountBox, amountField, depositBtn, withdrawBtn, status);
    }

    public TransactionManagementUI() {
        setPadding(new Insets(10));
        setSpacing(10);
        Label title = new Label("Transaction Management (All Accounts)");

        ComboBox<Account> accountBox = new ComboBox<>();
        accountBox.getItems().addAll(DemoContext.getAccounts().getAllAccounts());
        accountBox.setPromptText("Select Account");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");

        Label status = new Label();

        depositBtn.setOnAction(e -> {
            Account acc = accountBox.getValue();
            if (acc == null) { status.setText("Select an account."); return; }
            try {
                double amt = Double.parseDouble(amountField.getText());
                Transaction tx = Transactions.deposit(acc, amt, "UI Deposit", DemoContext.getStore());
                status.setText(tx.getType() + " of " + tx.getAmount() + " completed. New balance: " + acc.getBalance());
            } catch (NumberFormatException ex) {
                status.setText("Invalid amount.");
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });

        withdrawBtn.setOnAction(e -> {
            Account acc = accountBox.getValue();
            if (acc == null) { status.setText("Select an account."); return; }
            try {
                double amt = Double.parseDouble(amountField.getText());
                Transaction tx = Transactions.withdraw(acc, amt, "UI Withdrawal", DemoContext.getStore());
                status.setText(tx.getType() + " of " + tx.getAmount() + " completed. New balance: " + acc.getBalance());
            } catch (NumberFormatException ex) {
                status.setText("Invalid amount.");
            } catch (InsufficientBalanceException ex) {
                status.setText(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });

        getChildren().addAll(title, accountBox, amountField, depositBtn, withdrawBtn, status);
    }
}
