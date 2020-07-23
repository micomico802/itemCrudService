package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String dbName = "test_db";
    private final static String timezone = "?characterEncoding=UTF-8&serverTimezone=JST";
    private final static String userName = "root";
    private final static String password = "password";
    private static Connection conn;

    public static Connection init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // MySQL に接続
            conn = DriverManager.getConnection(url + dbName + timezone, userName, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection connection) {
        conn = connection;
        try {
            if (conn != null)
                conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
