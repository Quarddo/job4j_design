package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Массив с числами
     */
    private int[] data;
    /**
     * Позиция указателя в массиве
     */
    private int index = 0;

    /**
     * Конструктор для инициализации массива
     * @param data - массив
     */
    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     В данном методе цикл while пропускает нечетные элементы,
     а оператор return проверяет коректность найденного индекса
     и возвращает четное число
     */
    @Override
    public boolean hasNext() {
        while (index < data.length
                && data[index] % 2 != 0) {
            index++;
        }
            return index < data.length;
    }

    /**
     * Метод возвращает очередное четное число, либо выкидывает
     * exception (исключение) о его отсутствии.
     * @return - очередное четное значение.
     * @throws NoSuchElementException - выбрасывает если четный значений больше нет.
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
            return data[index++];
    }
}
