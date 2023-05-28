package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoJDBCImpl();

    public UserServiceImpl() {      // Вызывается объектом в методе main и в единственном тестовом файле UserServiceTest
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("\tМЕТОД ДОБАВЛЕНИЕ USERa В ТАБЛИЦУ\n");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("\tМЕТОД УДАЛЕНИЯ USERa ИЗ ТАБЛИЦЫ ( по id )");
    }

    public List<User> getAllUsers() {
        System.out.println("\tМЕТОД ПОЛУЧЕНИЕ ВСЕХ USER(ов) ИЗ ТАБЛИЦЫ");
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("\tМЕТОД УДАЛЕНИЕ ТАБЛИЦЫ User(ов)");
    }

}