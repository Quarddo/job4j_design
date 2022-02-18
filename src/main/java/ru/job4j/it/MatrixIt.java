package ru.job4j.it;

import java.util.Iterator;
import java.util.Arrays.*;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.length > row;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (data[row].length == 0) {
            while (data[row].length == 0) {
                row++;
                column = 0;
            }
        }
        Integer result = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return result;
    }
}
