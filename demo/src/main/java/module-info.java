module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.bank to javafx.fxml;
    opens com.bank.ui to javafx.fxml;
    exports com.bank;
    exports com.bank.ui;
}
