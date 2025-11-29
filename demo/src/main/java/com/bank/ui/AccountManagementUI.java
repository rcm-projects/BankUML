package com.bank.ui;

import com.bank.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;

public class AccountManagementUI extends VBox {
    public AccountManagementUI(User user) {
        setPadding(new Insets(10));
        setSpacing(10);
        Label title = new Label("Account Management");

        ListView<Account> accountsList = new ListView<>();
        accountsList.getItems().addAll(user.getAccounts());

        TextArea details = new TextArea();
        details.setEditable(false);
        details.setPrefRowCount(6);

        accountsList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Account#: ").append(newV.getAccountNumber()).append('\n');
                sb.append("Balance: ").append(newV.getBalance()).append('\n');
                if (newV.getCustomer() != null) {
                    sb.append("Owner: ").append(newV.getCustomer().getName()).append('\n');
                }
                details.setText(sb.toString());
            }
        });

        getChildren().addAll(title, accountsList, details);
    }
}
