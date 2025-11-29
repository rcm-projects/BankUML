package com.bank;


import com.bank.ui.AccountManagementUI;
import com.bank.ui.TransactionManagementUI;
import com.bank.ui.CustomerManagementUI;
import com.bank.ui.AdminPanelUI;
import com.bank.DemoContext;
import com.bank.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank UML App - JavaFX Frontend");

        // Login UI
        VBox loginPane = new VBox(10);
        loginPane.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome to MyBankUMLApp!");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Customer", "Teller", "SysAdmin");
        roleBox.setPromptText("Select Role");
        Button loginButton = new Button("Login");
        Label loginStatus = new Label();

        loginPane.getChildren().addAll(welcomeLabel, usernameField, passwordField, roleBox, loginButton, loginStatus);
        Scene loginScene = new Scene(loginPane, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                loginStatus.setText("Please fill username and password.");
                return;
            }

            AuthManager mgr = new AuthManager(DemoContext.getStore());
            java.util.Optional<User> opt = mgr.login(username, password);
            if (opt.isPresent()) {
                User u = opt.get();
                String role = "Customer";
                switch (u.getPerms().getRole()) {
                    case TELLER: role = "Teller"; break;
                    case ADMIN: role = "SysAdmin"; break;
                    default: role = "Customer"; break;
                }
                showDashboard(primaryStage, role, username);
            } else {
                loginStatus.setText("Invalid credentials.");
            }
        });
    }

    private void showDashboard(Stage stage, String role, String username) {
        BorderPane dashboard = new BorderPane();
        Label greeting = new Label("Logged in as " + role + ": " + username);
        dashboard.setTop(greeting);
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(10));

        // Center pane placeholder
        StackPane centerPane = new StackPane();
        dashboard.setCenter(centerPane);
        // try to locate current user in demo store
        User currentUser = DemoContext.findUserByUsername(username);

        if ("Customer".equals(role)) {
            Button viewAccounts = new Button("View Accounts");
            Button makeTransaction = new Button("Make Transaction");
            Button viewTransactions = new Button("View Transactions");
            menu.getChildren().addAll(viewAccounts, makeTransaction, viewTransactions);
            viewAccounts.setOnAction(e -> centerPane.getChildren().setAll(new AccountManagementUI(currentUser)));
            makeTransaction.setOnAction(e -> centerPane.getChildren().setAll(new TransactionManagementUI(currentUser)));
            viewTransactions.setOnAction(e -> centerPane.getChildren().setAll(new TransactionManagementUI(currentUser)));
        } else if ("Teller".equals(role)) {
            Button manageCustomers = new Button("Manage Customers");
            Button processCheck = new Button("Process Check");
            menu.getChildren().addAll(manageCustomers, processCheck);

            manageCustomers.setOnAction(e -> centerPane.getChildren().setAll(new CustomerManagementUI()));
            processCheck.setOnAction(e -> centerPane.getChildren().setAll(new TransactionManagementUI()));
        } else if ("SysAdmin".equals(role)) {
            Button adminPanel = new Button("Admin Panel");
            menu.getChildren().add(adminPanel);

            adminPanel.setOnAction(e -> centerPane.getChildren().setAll(new AdminPanelUI()));
        }

        dashboard.setLeft(menu);
        Scene dashboardScene = new Scene(dashboard, 600, 400);
        stage.setScene(dashboardScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
