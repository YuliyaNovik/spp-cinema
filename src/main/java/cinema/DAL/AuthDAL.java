package cinema.DAL;

import cinema.Model.Auth;
import cinema.Model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthDAL {
    private List<Auth> authList = new ArrayList<Auth>();

    public void addUser(Auth auth) {
        Integer updateIndex = null;
        for (Auth authUser: authList) {
            if (authUser.user.email.equals(auth.user.email)) {
                updateIndex = authList.indexOf(authUser);
            }
        }

        if (updateIndex != null) {
            authList.remove((int) updateIndex);
        }
        authList.add(auth);
    }

    public Auth getUser(String token) {
        for (Auth authUser: authList) {
            if (authUser.token.equals(token)) {
                return authUser;
            }
        }
        return null;
    }
}
