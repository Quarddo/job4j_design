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
    private int index;

    /**
     * Конструктор для инициализации массива
     * @param data - массив
     */
    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод выполняет поиск ячейки в которой находиться четное число,
     * при этом последующий поиск элемента будет уменьшать количество итераций цикла
     * на index (позиция указателя в массиве). Если очередное четное число не найдено
     * то метод вернет -1.
     * @return - возвращает индекс ячейки где находится четное число.
     */
    private Integer exist() {
        int value = -1;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                index = i;
                value++;
                break;
            }
        }
        return value;
    }

    /**
     * Метод проверяет есть ли в массиве следующее четное число.
     * Если метод exist возвращает 0 значит значение есть.
     * @return - возвращает true если значение есть.
     */
    @Override
    public boolean hasNext() {
        return exist() == 0;

    }

    /**
     * Метод возвращает очередное четное число, либо выкидывает
     * exception (исключение) о его отсутствии.
     * @return - очередное четное значение.
     * @throws NoSuchElementException - выбрасывает если четный значений больше нет.
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (hasNext()) {
            return data[index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
