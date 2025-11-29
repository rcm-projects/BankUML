package com.bank;

public class SysAdmin {

    private Permissions perms;

    public SysAdmin(Permissions perms) {
        this.perms = perms;
    }

    public void addUser(User user, Accounts accounts) {
        if (perms.hasPermission("ADD_USER")) {
            accounts.addUser(user);
        } else {
            throw new SecurityException("Permission denied");
        }
    }

    public void removeUser(User user, Accounts accounts) {
        if (perms.hasPermission("REMOVE_USER")) {
            accounts.removeUser(user);
        } else {
            throw new SecurityException("Permission denied");
        }
    }

    public Permissions getPermissions() {
        return perms;
    }
}

