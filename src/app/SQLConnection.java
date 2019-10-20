package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    // private static final String DRIVER = "com.mysql.jdbc.Driver";
    // private static boolean driverSet = false;
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

    // private static boolean loadDriver() {
    //     try {
    //         Class.forName(DRIVER);
    //     } catch (ClassNotFoundException e) {
    //         System.out.println(" MySQL JDBC Driver not found!");
    //         return false;
    //     }
    //     System.out.println("MySQL JDBC Driver Registered!");
    //     driverSet = true;
    //     return true;
    // }

    public boolean establishConnection(String url, String root, String password) {
        try {
            // if (driverSet || loadDriver()) {
        	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                connection = DriverManager.getConnection(url, root, password);
                return true;
            // }
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
        return false;
    }

    private boolean executeUpdateStatement(String statement) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            System.out.println("Issue executing update statement!");
            return false;
        }
        return true;
    }

    public boolean executeStatement(String statement) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(statement);
            return true;
        } catch (Exception e) {
            System.out.println("Issue executing statement!");
        }
        return false;
    }

    public Object executeSelectStatement(String statement, String columnLabel) {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            System.out.println("Issue executing statement!");
        }
        return new Object();
    }

    private Object checkElement(String tableName, String element) {
        return executeSelectStatement("SELECT " + element + " FROM " + tableName, tableName);
    }

    private Object checkElement(String tableName, String element, String filter) {
        return executeSelectStatement("SELECT " + element + " FROM " + tableName + " WHERE " + filter, tableName);
    }

    public boolean input_query(String tableName, String element, String value) {
        return executeUpdateStatement("INSERT INTO " + tableName + element + " VALUES " + value);
    }

    public boolean input_query(String tableName, String element, String value, String filter) {
        return executeUpdateStatement("INSERT INTO " + tableName + " SET " + element + " " + "WHERE " + filter);
    }

    public boolean update_query(String tableName, String element, String value) {
        if (checkElement(tableName, element).getClass() == Object.class)
            input_query(tableName, element, value);
        return executeUpdateStatement("UPDATE " + tableName + " SET " + element);
    }

    public boolean update_query(String tableName, String element, String value, String filter) {
        if (checkElement(tableName, element, filter).getClass() == Object.class) // Check if obj is not empty
            input_query(tableName, element, value, filter);
        return executeUpdateStatement("UPDATE " + tableName + " SET " + element + " " + "WHERE " + filter);
    }
}