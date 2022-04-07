package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** Преобразуем объек User в Json и обратно */

public class Main {
    public static void main(String[] args) {
        final User user = new User(true, 26, "Maksim",
                new Address("Pushkina"),
                new String[] {"Teriushov", "Married"});
        /** Преобразуем объект user в json - строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));

        /** Модифицируем json-строку */
        final String userJson =
                "{"
                        + "\"name\":Maksim,"
                        + "\"age\":26,"
                        + "\"car\":true,"
                        + "\"address\":"
                        + "{"
                        + "\"street\":Pushkina"
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\", \"Free\"]"
                        + "}";

        final User userMod = gson.fromJson(userJson, User.class);
        System.out.println(userMod);
    }
}
