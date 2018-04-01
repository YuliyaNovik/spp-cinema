package cinema.Helper;

import cinema.Enum.Role;

public class RoleHelper {
    private static final String DEFAULT = "default";
    private static final String ADMIN = "admin";
    private static final String MOVIE_ADMIN = "movie_admin";
    private static final String ACCOUNTANT = "accountant";

    public static Role getRole(String name) {
        switch (name) {
            case RoleHelper.DEFAULT:
                return Role.DEFAULT;
            case RoleHelper.ADMIN:
                return Role.ADMIN;
            case RoleHelper.MOVIE_ADMIN:
                return Role.MOVIE_ADMIN;
            case RoleHelper.ACCOUNTANT:
                return Role.ACCOUNTANT;
        }
        return null;
    }

    public static String getDbRole(Role role) {
        if (role == Role.ADMIN) {
            return RoleHelper.ADMIN;
        } else if (role == Role.MOVIE_ADMIN) {
            return RoleHelper.MOVIE_ADMIN;
        } else if (role == Role.ACCOUNTANT) {
            return RoleHelper.ACCOUNTANT;
        } else {
            return RoleHelper.DEFAULT;
        }
    }
}
