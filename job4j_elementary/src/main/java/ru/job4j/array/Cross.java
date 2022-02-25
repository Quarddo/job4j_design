package ru.job4j.array;

public class Cross {
    public static void chek(int[] left, int[] right) {
        for (int i = 0; i < left.length; i++) {
            for( int j = 0; j < right.length; j++) {
                if (left[i] == right[j]) {
                    System.out.println(i);
                }
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] left = new int[]{1, 2};
        int[] right = new int[]{2, 1};
        Cross.chek(left, right);
        System.out.println(left);
    }
}
