package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private  boolean car;
    private  int age;
    private  String name;
    private  Address address;
    private  String[] statuses;

    public User() {

    }

    public User(boolean car, int age, String name, Address address, String[] statuses) {
        this.car = car;
        this.age = age;
        this.name = name;
        this.address = address;
        this.statuses = statuses;
    }
    public boolean isCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "User{"
                + "car=" + car
                + ", age=" + age
                + ", name=" + name
                + ", street=" + address
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        /** JSONObject из json-строки */
        JSONObject jsonAddress = new JSONObject("{\"street\":\"Pushkina\"}");
        /** JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Teriushov");
        list.add("Married");
        JSONArray jsonStatuses = new JSONArray(list);
        /** JSONObject напрямую методом put */
        final User user = new User(true, 26, "Maksim",
                new Address("Pushkina"),
                new String[] {"Teriushov", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("car", user.isCar());
        jsonObject.put("age", user.getAge());
        jsonObject.put("name", user.getName());
        jsonObject.put("address", user.getAddress());
        jsonObject.put("statuses", user.getStatuses());
        /** Выведем результат на консоль */
        System.out.println(jsonObject);
        /** Преобразуем объект user в json-строку */
        System.out.println(new JSONObject(user));
    }
}
