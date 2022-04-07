package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private final boolean car;
    private final int age;
    private final String name;
    private final Address address;
    private final String[] statuses;

    public User(boolean car, int age, String name, Address address, String[] statuses) {
        this.car = car;
        this.age = age;
        this.name = name;
        this.address = address;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "User{"
                + ", car=" + car
                + ", age=" + age
                + "name=" + name
                + ", street=" + address
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
