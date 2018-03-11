package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Role;
import cinema.Model.User;

public class UserService {
    public User getUser(String userName, String password) {
        User user = DAL.getUserDAL().getUserByName(userName);
        if (user == null) {
            user = DAL.getUserDAL().getUserByEmail(userName);
        }

        if (!user.password.equals(password)) {
            return null;
        }
        return user;
    }

    public User getUser(String token) {
        return new User();
    }

    public boolean createUser(User user) {
        User existUser = DAL.getUserDAL().getUserByName(user.userName);
        if (existUser == null) {
            existUser = DAL.getUserDAL().getUserByEmail(user.userName);
        }

        if (existUser != null) {
            return false;
        }
        user.role = Role.DEFAULT;
        return DAL.getUserDAL().insertUser(user);
    }
}
