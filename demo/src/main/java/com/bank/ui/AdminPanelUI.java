package com.bank.ui;

import com.bank.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;

public class AdminPanelUI extends VBox {
    public AdminPanelUI() {
        setPadding(new Insets(10));
        setSpacing(10);
        Label title = new Label("Admin Panel");

        // Add user form
        TextField idField = new TextField(); idField.setPromptText("ID");
        TextField usernameField = new TextField(); usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField(); passwordField.setPromptText("Password");
        ComboBox<String> roleBox = new ComboBox<>(FXCollections.observableArrayList("Customer","Teller","SysAdmin"));
        roleBox.setPromptText("Role");
        Button addUserBtn = new Button("Add User");

        HBox addBox = new HBox(8, idField, usernameField, passwordField, roleBox, addUserBtn);

        // Existing users list and remove
        ListView<User> usersList = new ListView<>();
        usersList.getItems().addAll(DemoContext.getStore().getAllUsers());
        Button removeBtn = new Button("Remove Selected User");

        addUserBtn.setOnAction(e -> {
            String id = idField.getText();
            String uname = usernameField.getText();
            String pwd = passwordField.getText();
            String role = roleBox.getValue();
            if (id == null || id.isEmpty() || uname == null || uname.isEmpty() || pwd == null || pwd.isEmpty() || role == null) {
                return;
            }
            int roleCode = 0;
            if ("Customer".equals(role)) roleCode = 0;
            if ("Teller".equals(role)) roleCode = 1;
            if ("SysAdmin".equals(role)) roleCode = 2;

            Permissions perms = new Permissions(uname, pwd, roleCode);
            User user = new User(id, uname, pwd, "", "", perms);
            // add to datastore and accounts via sysadmin
            DemoContext.getStore().addUser(user);
            try {
                DemoContext.getSysAdmin().addUser(user, DemoContext.getAccounts());
            } catch (SecurityException ex) {
                // ignore for demo or show message
            }
            usersList.getItems().add(user);
            idField.clear(); usernameField.clear(); passwordField.clear(); roleBox.setValue(null);
        });

        removeBtn.setOnAction(e -> {
            User sel = usersList.getSelectionModel().getSelectedItem();
            if (sel == null) return;
            try {
                DemoContext.getSysAdmin().removeUser(sel, DemoContext.getAccounts());
            } catch (SecurityException ex) {
                // ignore for demo
            }
            DemoContext.getStore().removeUser(sel);
            usersList.getItems().remove(sel);
        });

        getChildren().addAll(title, addBox, usersList, removeBtn);
    }
}
