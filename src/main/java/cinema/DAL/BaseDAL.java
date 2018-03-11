package cinema.DAL;

import java.sql.*;

class BaseDAL {
    private static final String url = "jdbc:mysql://localhost:3306/cinema?autoReconnect=true&useSSL=false";
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
