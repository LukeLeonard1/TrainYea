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
            	e.printStackTrace();
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
            System.out.println(statement);
            stmt.executeUpdate(statement);
            con.close();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Issue executing update statement!");
            return false;
        }
    }

    public boolean executeStatement(String statement) {
        try {
            loadDriver();
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            System.out.println(statement);
            stmt.execute(statement);
            con.close();
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Issue executing statement!");
        }
        return false;
    }

    public Object executeSelectStatement(String statement, String columnLabel) {
        try {
            loadDriver();
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            System.out.println(statement);
            ResultSet rs = stmt.executeQuery(statement);
            if (rs.next()) {
                return rs.getObject(1);
            }
            con.close();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Issue executing statement!");
        }
        return new Object();
    }

    private Object checkElement(String tableName, String element, String filter) {
        return executeSelectStatement("select " + element + " from " + tableName + " where (" + filter+")", tableName);
    }

    public boolean input_query(String tableName, String elements, String values) {
        return executeUpdateStatement("INSERT INTO " + tableName +"("+ elements + ") VALUES (" + values+")");
    }

    public boolean update_query(String tableName, String element, String value, String filter, String defaultElms, String defaultVals) {
        if (checkElement(tableName, element, filter).getClass() == Object.class) // Check if obj is not empty
            input_query(tableName, defaultElms, defaultVals);
        return executeUpdateStatement("UPDATE " + tableName + " SET " + element + "="+value + " WHERE (" + filter+")");
    }
}