package ru.job4j.loop;

public class Factorial {
    public static int calc(int n) {
        int multi = 1;
        for (int index = 2; index <= n; index++) {
            multi *= index;
        }
        return multi;
    }
}
