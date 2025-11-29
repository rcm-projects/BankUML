package com.bank;

public class Permissions {

    private final String username;
    private final String password;
    private final int perms; // 0=Customer, 1=Teller, 2=Admin

    public Permissions(String username, String password, int perms) {
        this.username = username;
        this.password = password;
        this.perms = perms;
    }

    public String getUsername() {
        return username;
    }
    
 public boolean hasPermission(String requiredPerm) {
        if (requiredPerm == null) return false;
        // Simple permission mapping based on role integer
        switch (requiredPerm) {
            case "ADD_USER":
            case "REMOVE_USER":
                return isAdmin();
            case "SEARCH_ACCOUNTS":
                return isTeller() || isAdmin();
            default:
                return false;
        }
    }

   

    public String getPassword() {
        return password;
    }

    public int getPerms() {
        return perms;
    }

    public Role getRole() {
        switch (perms) {
            case 1: return Role.TELLER;
            case 2: return Role.ADMIN;
            default: return Role.CUSTOMER;
        }
    }
    

    public boolean isCustomer() { return getRole() == Role.CUSTOMER; }
    public boolean isTeller()   { return getRole() == Role.TELLER; }
    public boolean isAdmin()    { return getRole() == Role.ADMIN; }
}
