package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() { /* Пустой конструктор */}

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String name() { return name; }

    public String getName() { return name; }

    public String getLastName() { return lastName; }

    public Byte getAge() { return age; }

    @Override
    public String toString() {
        return "[User " +
                "id: " + id +
                ", name: " + name +
                ", lastname: " + lastName +
                ", age: " + age +
                ']';
    }

}
