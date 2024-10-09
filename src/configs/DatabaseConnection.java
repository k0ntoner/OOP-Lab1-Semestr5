package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tariffsdb";
    private static final String USER = "root";
    private static final String PASSWORD = "19112004";
    public static Connection getConnection()  throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
