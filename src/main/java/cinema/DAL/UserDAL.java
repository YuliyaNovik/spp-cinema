package cinema.DAL;

import cinema.Helper.RoleHelper;
import cinema.Helper.SQLQuery;
import cinema.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int USER_NAME_INDEX = 2;
    private static final int PASSWORD_INDEX = 3;
    private static final int EMAIL_INDEX = 4;
    private static final int ROLE_INDEX = 5;

    public User getUserByName(String name) {
        String query = String.format(SQLQuery.getUserByName, name);

        List<User> users = get(query);
        return users.size() > 0 ? users.get(0) : null;
    }

    public User getUserByEmail(String email) {
        String query = String.format(SQLQuery.getUserByEmail, email);

        List<User> users = get(query);
        return users.size() > 0 ? users.get(0) : null;
    }


    public User getById(int id) {
        String query = String.format(SQLQuery.getUserById, id);
        List<User> users = get(query);
        return users.size() > 0 ? users.get(0) : null;
    }

    public List<User> getAll() {
        return get(SQLQuery.getAllUser);
    }

    public List<User> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteUser, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(User user) {
        String query = String.format(
                SQLQuery.updateUser,
                user.userName,
                user.email,
                user.password,
                RoleHelper.getDbRole(user.role),
                user.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(User user) {
        String query = String.format(
            SQLQuery.insertUser,
            user.userName,
            user.email,
            user.password,
            RoleHelper.getDbRole(user.role)
        );
        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<User> get(String query) {
        ResultSet result = openConnection(query);

        List<User> users = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                User user = new User();
                user.userName = result.getString(UserDAL.USER_NAME_INDEX);
                user.password = result.getString(UserDAL.PASSWORD_INDEX);
                user.email = result.getString(UserDAL.EMAIL_INDEX);
                user.role = RoleHelper.getRole(result.getString(UserDAL.ROLE_INDEX));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return users;
    }
}
