package com.bank;

public class DemoContext {

    private static final DataStore store = new DataStore();
    private static final Accounts accounts = new Accounts();
    private static final SysAdmin sysAdmin;

    static {
        // create permissions
        Permissions customerPerms = new Permissions("alice", "pass123", 0);
        Permissions tellerPerms = new Permissions("teller", "tpass", 1);
        Permissions adminPerms = new Permissions("admin", "root", 2);

        // create users
        User customerUser = new User("C001", "alice", "pass123", "Montreal", "2000-01-01", customerPerms);
        User tellerUser = new User("T001", "teller", "tpass", "Montreal", "1995-05-05", tellerPerms);
        User adminUser = new User("A001", "admin", "root", "Montreal", "1990-03-03", adminPerms);

        // create sample customer entity and account
        Customer customer = new Customer("Alice Customer");
        Card aliceCard = new Card(customer, "ACC-1001", 500.00);

        // register into store and accounts
        store.addAccount(aliceCard);
        store.addUser(customerUser);
        store.addUser(tellerUser);
        store.addUser(adminUser);

        accounts.addUser(customerUser);
        accounts.addUser(tellerUser);
        accounts.addUser(adminUser);

        accounts.registerAccount(customerUser, aliceCard);

        sysAdmin = new SysAdmin(adminPerms);
    }

    public static DataStore getStore() {
        return store;
    }

    public static Accounts getAccounts() {
        return accounts;
    }

    public static SysAdmin getSysAdmin() {
        return sysAdmin;
    }

    public static User findUserByUsername(String username) {
        return store.getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
