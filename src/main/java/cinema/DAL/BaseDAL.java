package cinema.DAL;

import java.sql.*;

public class BaseDAL {
    private static final String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&characterEncoding=UTF8";
    private static final String user = "root";
    private static final String password = "1610-19";

    private static Connection connection;
    private static Statement statement;

    ResultSet openConnection(String query) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return null;
        }
    }

    boolean openUpdateConnection(String query) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
    }

    void closeConnection(ResultSet result) {
        try {
            connection.close();
            statement.close();
            if (result != null) {
                result.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
