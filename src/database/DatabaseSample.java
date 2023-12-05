package database;

import java.sql.*;

public class DatabaseSample {

    static String DB_URL = "jdbc:mysql://127.0.0.1:3306/bankapp";
    static String DB_USERNAME = "root";
    static String DB_PASSWORD = "test123";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dbWrite(String user, String pass) {
        try {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user);
            statement.setString(2, pass);
            statement.executeUpdate();

            System.out.println("Record created.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dbRead() {
        try {
            String sql = "SELECT username, password FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 4);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String column1 = result.getString("username");
                String column2 = result.getString("password");
                System.out.println("Column 1: " + column1);
                System.out.println("Column 2: " + column2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
