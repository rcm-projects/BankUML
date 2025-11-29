package com.bank.ui;

import com.bank.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class CustomerManagementUI extends VBox {
    public CustomerManagementUI() {
        setPadding(new Insets(10));
        setSpacing(10);
        Label title = new Label("Customer Management");

        TextField searchField = new TextField();
        searchField.setPromptText("Search by username or id");
        Button searchBtn = new Button("Search");

        ListView<User> results = new ListView<>();

        TextArea details = new TextArea();
        details.setEditable(false);
        details.setPrefRowCount(6);

        HBox searchBox = new HBox(8, searchField, searchBtn);

        searchBtn.setOnAction(e -> {
            String q = searchField.getText();
            results.getItems().clear();
            if (q == null || q.isEmpty()) {
                results.getItems().addAll(DemoContext.getStore().getAllUsers());
            } else {
                DemoContext.getStore().getAllUsers()
                        .stream()
                        .filter(u -> u.getUsername().contains(q) || u.getId().contains(q))
                        .forEach(results.getItems()::add);
            }
        });

        results.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(newV.getId()).append('\n');
                sb.append("Username: ").append(newV.getUsername()).append('\n');
                sb.append("DOB: ").append(newV.getDob()).append('\n');
                sb.append("Birthplace: ").append(newV.getBirthPlace()).append('\n');
                sb.append("Accounts:\n");
                newV.getAccounts().forEach(a -> sb.append(" - ").append(a.getAccountNumber()).append(" (bal: ").append(a.getBalance()).append(")\n"));
                details.setText(sb.toString());
            }
        });

        getChildren().addAll(title, searchBox, results, details);
    }
}
