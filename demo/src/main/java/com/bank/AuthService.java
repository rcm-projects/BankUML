package com.bank;

public class AuthService {

    private AuthService() {}

    public static boolean canViewAccount(User requester, Account account) {
        Role role = requester.getPerms().getRole();

        if (role == Role.ADMIN || role == Role.TELLER)
            return true;

        return requester.getAccounts().contains(account); // customer: own accounts only
    }

    public static boolean canSearchAccounts(Permissions perms) {
        Role role = perms.getRole();
        return role == Role.TELLER || role == Role.ADMIN;
    }

    public static boolean canManageRoles(Permissions perms) {
        return perms.getRole() == Role.ADMIN;
    }
}
