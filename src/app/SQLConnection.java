package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLConnection {
    // private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static boolean driverSet = false;
    private Connection conn;
    String url = "url";
    String user = "user";
    String pass = "pass";

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

    public void getConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", pass);
        try {
            loadDriver();
            conn = DriverManager.getConnection("jdbc:" + "mysql" + "://" + "127.0.0.1" + ":" + "3307" + "/" + "",
                    connectionProps);
        } catch (SQLException ex) {
            System.out.println("SQL Connection Failed!");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Connection Failed!");
            ex.printStackTrace();
        }
    }

    public void executeMySQLQuery() {
        Statement stmt = null;
        ResultSet resultset = null;

        try {
            stmt = conn.createStatement();
            resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }

            while (resultset.next()) {
                System.out.println(resultset.getString("Database"));
            }
        } catch (SQLException ex) {
            System.out.println("SQL execute Connection Failed!");
            ex.printStackTrace();
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException sqlEx) {
                }
                resultset = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                }
                conn = null;
            }
        }
    }

    private boolean executeUpdateStatement(String statement) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            System.out.println("Issue executing update statement!");
            return false;
        }
        return true;
    }

    public boolean executeStatement(String statement) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(statement);
            return true;
        } catch (Exception e) {
            System.out.println("Issue executing statement!");
        }
        return false;
    }

    public Object executeSelectStatement(String statement, String columnLabel) {
        try (Statement stmt = conn.createStatement()) {
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