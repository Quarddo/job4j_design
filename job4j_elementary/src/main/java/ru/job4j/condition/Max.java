package ru.job4j.condition;

public class Max {
    public static int max(int left, int right) {
        boolean condition = left >= right;
        int result = condition ? left : right;
        return result;
    }

    public static void main(String[] args) {
        int sq = Max.max(3, 2);
        System.out.println(sq);
    }
}
