package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static boolean driverSet = false;
    // private Connection con; //TODO: bigger scope
    String url = "jdbc:mysql://localhost:3307/TrainYea?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "";

    private static boolean loadDriver() {
        if (!driverSet) {
            try {
                // Class.forName(DRIVER);
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            } catch (SQLException e) {
                System.out.println(" MySQL JDBC Driver not found!");
                return false;
            }
            System.out.println("MySQL JDBC Driver Registered!");
        }
        driverSet = true;
        return true;
    }

    private boolean executeUpdateStatement(String statement) {
        try {
            loadDriver();
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(statement);
            con.close();
        } catch (Exception e) {
            System.out.println("Issue executing update statement!");
            return false;
        }
        return true;
    }

    public boolean executeStatement(String statement) {
        try {
            loadDriver();
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            stmt.execute(statement);
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Issue executing statement!");
        }
        return false;
    }

    public Object executeSelectStatement(String statement, String columnLabel) {
        try {
            loadDriver();
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            con.close();
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