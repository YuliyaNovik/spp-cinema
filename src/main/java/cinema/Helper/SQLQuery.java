package cinema.Helper;

public class SQLQuery {
    public static String getUserByName = "SELECT * FROM users WHERE user_name='%s'";
    public static String getUserByEmail = "SELECT * FROM users WHERE email='%s'";
    public static String insertUser = "INSERT INTO users (user_name, email, password, role) VALUES ('%s', '%s', '%s', '%s')";
}
