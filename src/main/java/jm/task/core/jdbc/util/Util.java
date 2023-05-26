package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rendb_pp114?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("\nUtil.getConnection with DB is good!!!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nUtil.getConnection with DB - Error\n");
        }
        return connection;
    }
    private Util() {

    }
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("\nUtil.closeConnection БД успешно закрыта!!!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nUtil.closeConnection Error закрытия соединения с БД\n");
        }
    }


}
