package ru.job4j.map;

import java.util.Calendar;

/**
 * Модель User
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Конструктор User
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
