package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;


public class ResultFile {

public static void main(String[] args) {
    try (FileOutputStream out = new FileOutputStream("result.txt")) {
        int[][] data = multiple(9);
        for (int[] row : data) {
            out.write(Arrays.toString(row).getBytes());
            out.write(System.lineSeparator().getBytes());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private static int[][] multiple(int size) {
        int[][] data = new int[size][size];
        for (int row = 0; row < data.length; row++) {
            for (int cell = 0; cell < data.length; cell++) {
                data[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return data;
    }
}