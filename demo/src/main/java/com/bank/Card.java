package com.bank;

public class Card extends Account {

    public Card(Customer customer) {
        super(customer);
    }

    public Card(Customer customer,
                String accountNumber,
                double openingBalance) {
        super(customer, accountNumber, "CARD", openingBalance);
    }

    @Override
    public void pay() {
        System.out.println("Card payment for: " + customer.getName());
    }

    @Override
    public void receipt() {
        System.out.println("Card receipt for: " + customer.getName());
    }
}
