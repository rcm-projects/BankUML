package com.bank;

public class Check extends Account {

    public Check(Customer customer) {
        super(customer);
        this.accountType = "CHECK";
    }

    public Check(Customer customer, String accountNumber, double openingBalance) {
        super(customer, accountNumber, "CHECK", openingBalance);
    }

    @Override
    public void pay() {
        // Behavior handled by Transaction/Service layer according to the report
    }

    @Override
    public void receipt() {
        // Behavior handled by Transaction/Service layer according to the report
    }
}
