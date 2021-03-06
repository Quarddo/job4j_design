package ru.job4j.map;

import java.util.*;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        User user = (User) obj;
        return user.name == this.name
                && user.children == this.children
                && user.birthday == this.birthday;
    }
/**
    public static void main(String[] args) {
        User us1 = new User("Max", 1, new GregorianCalendar(1995, Calendar.MAY, 21));
        User us2 = new User("Max", 1, new GregorianCalendar(1995, Calendar.MAY, 21));

        ru.job4j.map.Map<User, Object> map = new HashMap<>(
                Map.of(
                        us1, new Object(),
                        us2, new Object())
        );
        System.out.println(map);
        System.out.println(us1.hashCode());
        System.out.println(us2.hashCode());
    }
    */
}