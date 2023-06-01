package jm.task.core.jdbc;

/**
 * В рамках этой задачи необходимо реализовать взаимодействие с базой данных с помощью Hibernate и дописать методы в
 * классе UserHibernateDaoImpl, проверить свои методы заранее написанными JUnit тестами из заготовки.

 *    Требования к классам приложения:
 *  1. UserHibernateDaoImpl должен реализовывать интерефейс UserDao
 *  2. В класс Util должна быть добавлена конфигурация для Hibernate (рядом с JDBC), без использования xml.
 *  3. Service на этот раз использует реализацию dao через Hibernate
 *  4. Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl должны быть реализованы
 *     с помощью SQL.
 */

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Уинстон", "Черчилль", (byte) 90);
        userService.saveUser("Иосиф", "Сталин", (byte) 75);
        userService.saveUser("Рональд", "Рейган", (byte) 93);
        userService.saveUser("Маргарет", "Тетчер", (byte) 87);

        userService.getAllUsers();
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.sessionFactoryClosed();
    }

}