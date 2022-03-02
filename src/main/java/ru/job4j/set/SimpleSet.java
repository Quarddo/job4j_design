package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    /**
     * Метод добавляет новое уникальное значение в коллекцию
     * @param value добавляемое значение
     * @return true если добавляемый в коллекцию элемент уникальный,
     */
    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    /**
     * Метод сравнивает содержит ли коллекция данный елемент
     * если данный елемент присутствует в коллекции, метод возвращает true
     * @param value елемент,по которому осуществляется сравнение с коллекцией
     * @return true если елемент value есть в коллекции
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T obj : set) {
            if (Objects.equals(value, obj)) {
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * Метод осуществляет перебор коллекции
     * @return список елементов присутствующих в коллекции
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
