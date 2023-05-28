package jm.task.core.jdbc;

/**
 *          Необходимые операции:
 * 1.	 Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
 * 2.	 Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
 * 3.	 Очистка содержания таблицы
 * 4.	 Добавление User в таблицу
 * 5.	 Удаление User из таблицы (по id)
 * 6.	 Получение всех User(ов) из таблицы


 *         Алгоритм работы приложения:
 *    В методе main класса Main должны происходить следующие операции:
 * 1.	 Создание таблицы User(ов)
 * 2.	 Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль
 * (User с именем – name добавлен в базу данных)
 * 3.	 Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
 * 4.	 Очистка таблицы User(ов)
 * 5.	 Удаление таблицы
 */

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Уинстон", "Черчилль", (byte) 90);
        userService.saveUser("Иосиф", "Сталин", (byte) 75);
        userService.saveUser("Рональд", "Рейган", (byte) 93);
        userService.saveUser("Маргарет", "Тетчер", (byte) 87);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
    }

}