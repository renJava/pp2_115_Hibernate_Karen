package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rendb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("\n\n\nUtil.getConnection with DB is good!!!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nUtil.getConnection with DB - Error!\n");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("\nUtil.closeConnection БД успешно закрыта!!!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nUtil.closeConnection Error закрытия соединения с БД!\n");
        }
    }
    private Util () {
        throw new UnsupportedOperationException("Чисто служебный конструктор, чтобы закрыть неявный публичный");
    }

}
