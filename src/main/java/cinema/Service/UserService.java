package cinema.Service;

import cinema.DAL.DAL;
import cinema.Enum.Role;
import cinema.Model.User;
import cinema.Util.FilterUtil;

import java.util.List;

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

    public boolean createUser(User user) {
        User existUser = DAL.getUserDAL().getUserByName(user.userName);
        if (existUser == null) {
            existUser = DAL.getUserDAL().getUserByEmail(user.userName);
        }

        if (existUser != null) {
            return false;
        }
        user.role = Role.DEFAULT;
        return DAL.getUserDAL().insert(user);
    }

    public User getUser(int id) {
        return DAL.getUserDAL().getById(id);
    }

    public List<User> getAllUser() {
        return DAL.getUserDAL().getAll();
    }

    public List<User> getUserByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "user");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getUserDAL().getByQuery(query);
    }

    public boolean updateUser(User user) {
        return DAL.getUserDAL().update(user);
    }

    public boolean deleteUser(int id) {
        return DAL.getUserDAL().deleteById(id);
    }
}
