package unienroll.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database is not configured yet.");
            e.printStackTrace();
            return null;
        }
    }
}