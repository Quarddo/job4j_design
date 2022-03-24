package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Failed to get parameters, given key does not exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Value not found.");
        }
        for (String arg : args) {
            String[] rsl = arg.split("=", 2);
            if (checkPattern(rsl)) {
                values.put(rsl[0].substring(1), rsl[1]);
            }
        }
    }

    private static boolean checkPattern(String[] rsl) {
        if (rsl.length != 2 || rsl[0].isEmpty()
                || !rsl[0].startsWith("-")
                || rsl[1].isEmpty()) {
            throw new IllegalArgumentException("Invalid value format.");
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
