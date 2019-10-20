package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static boolean driverSet = false;
    private Connection connection;

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(" MySQL JDBC Driver not found!");
            return false;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        driverSet = true;
        return true;
    }

    public boolean establishConnection(String url, String root, String password) {
        try {
            if (driverSet || loadDriver()) {
                connection = DriverManager.getConnection(url, root, password);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
        return false;
    }

    private boolean executeStatement(String statement) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            System.out.println("Issue executing statement!");
            return false;
        }
        return true;
    }

    public boolean update_query(String tableName, String elements) {
        return executeStatement("UPDATE " + tableName + " SET " + elements);
    }

    public boolean update_query(String tableName, String elements, String filter) {
        return executeStatement("UPDATE " + tableName + " SET " + elements + " " + "WHERE " + filter);
    }
}