package com.bank;

public class Saving extends Account {

    public Saving(Customer customer) {
        super(customer);
    }

    public Saving(Customer customer,
                  String accountNumber,
                  double openingBalance) {
        super(customer, accountNumber, "SAVING", openingBalance);
    }

    private void title() {
        System.out.println("**Savings Payment**");
    }

    @Override
    public void pay() {
        title();
        System.out.println("Payment from saving account for: " + customer.getName());
    }

    @Override
    public void receipt() {
        System.out.println("Payment receipt from saving account for: " + customer.getName());
    }
}
