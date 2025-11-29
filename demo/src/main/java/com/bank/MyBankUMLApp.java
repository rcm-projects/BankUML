
package com.bank;

public class MyBankUMLApp {

    public static void main(String[] args) {

        // Bottom-up style demonstration consistent with Integration Test #1 (p.43)
        DataStore store = new DataStore();

        // --- Create permissions (domain objects only) ---
        Permissions customerPerms = new Permissions("alice", "pass123", 0);
        Permissions tellerPerms = new Permissions("teller", "tpass", 1);
        Permissions adminPerms = new Permissions("admin", "root", 2);

        // --- Create Users consistent with UML and Component-Level Design ---
        User customerUser = new User("C001", "alice", "pass123",
                "Montreal", "2000-01-01", customerPerms);

        User tellerUser = new User("T001", "teller", "tpass",
                "Montreal", "1995-05-05", tellerPerms);

        User adminUser = new User("A001", "admin", "root",
                "Montreal", "1990-03-03", adminPerms);

        // --- Create Customer entity (domain only) ---
        Customer customer = new Customer("Alice Customer");

        // --- Create Account domain object (Card account as example) ---
        Card aliceCard = new Card(customer, "ACC-1001", 500.00);

        // --- Store objects into DataStore (testing-only mock store) ---
        store.addAccount(aliceCard);

        // No UI, no search calls here.
        // The report describes these actions as belonging to Service and UI layers.
        // --- Demonstration of bottom-up transaction validation (Integration Test #1) ---
        try {
            Transactions.deposit(aliceCard, 150.25, "Paycheck", store);
            Transactions.withdraw(aliceCard, 50.00, "ATM Withdrawal", store);

        } catch (InsufficientBalanceException | IllegalArgumentException e) {
            // Exceptions as defined in Component Tests / Error Handling (p.40–41)
        }

        // --- Admin/User/Teller interactions are described, not implemented here.
        // They belong to the UI and Service layers per the Architectural Design (p.23).
    }
}
