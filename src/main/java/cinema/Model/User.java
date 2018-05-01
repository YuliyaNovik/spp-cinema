package cinema.Model;

import cinema.Enum.Role;

public class User {
    public int id;
    public String userName;
    public String email;
    public String password;
    public Role role;

    @Override
    public String toString() {
        return userName + "|" + email + "|" + role.toString();
    }
}
