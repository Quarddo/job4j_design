package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private final String name;
    private final int age;
    private final boolean car;
    private final Address address;
    private final String[] statuses;

    public User(String name, int age, boolean car, Address address, String[] statuses) {
        this.name = name;
        this.age = age;
        this.car = car;
        this.address = address;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "User{"
                + "name=" + name
                + ", age=" + age
                + ", car=" + car
                + ", street=" + address
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
