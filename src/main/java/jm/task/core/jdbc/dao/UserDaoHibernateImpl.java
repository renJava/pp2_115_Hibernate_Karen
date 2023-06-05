package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl () {/* Пустой конструктор */}

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(45)," +
                    " lastname VARCHAR(45)," +
                    " age TINYINT)");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана и sessionFactory отлично коптит");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Таблица не создана и sessionFactory заглохла");
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("dropUsersTable: Таблицы и не было");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.println("Override метода Hibernate.saveUser. User c именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("saveUser: User'a невозможно добавить в базу");
//                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User user;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("Override метода Hibernate.removeUserById. User по id удален\n");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("removeUserById: User № " + id + " в БД и не было\n");
//                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            list = query.getResultList();
            for (User users : list) {
                System.out.println(users);
            }
            System.out.println("Override метода getAllUsers - полный список БД\n");
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\ngetAllUsers: Списка Userов и не было\n");
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("DELETE FROM User u");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица Users очищена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cleanUsersTable: Таблицы Users и не было\n");
        }
    }

}
