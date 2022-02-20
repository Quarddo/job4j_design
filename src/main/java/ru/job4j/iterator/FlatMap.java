package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class FlatMap<T> implements Iterator<T> {
    /**
     * data - итератор итераторов по всем значениям
     */
    private final Iterator<Iterator<T>> data;
    /**
     * текущий итератор.
     */
    private Iterator<T> cursor = Collections.emptyIterator();

    /**
     * Конструктор для инициализации итератора
     * @param data - итератор итераторов
     */
    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * Проверка наличия следующего итератора
     * @return - возвращает оочередной итератор
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext()
                && data.hasNext()) {
            cursor = data.next();
        }
            return cursor.hasNext();

    }

    /**
     * Метод next возвращает последовательно числа из вложенных итераторов,
     * или выбрасывает исключение.
     * @return очередное значение или exception.
     * NoSuchElementException - выбрасывается если больше элементов
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
