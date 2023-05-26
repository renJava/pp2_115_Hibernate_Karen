package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserService {
    Connection connection = Util.getConnection();


    @Override
    public void createUsersTable() {
        String callCreateTable = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(45), lastname VARCHAR(45), age TINYINT)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(callCreateTable);
            System.out.println("\nТаблица пользователей создана!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nСбой в создании новой таблицы пользователей, возможно уже существует\n");
        }

    }

    @Override
    public void dropUsersTable() {
        String callDropUsersTable = "INSERT INTO rendb_pp114.users (name, lastname, age) VALUES (?,?,?)";
        Connection connection = null;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(callDropUsersTable);
            System.out.println("\nТаблица  пользователей удалена!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nСбой удаления таблицы пользователей, возможно, и нема\n");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String callSaveUser = "INSERT INTO rendb_pp114.users (name, lastname, age) VALUES (?,?,?)";
        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(callSaveUser);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
    }

}
