package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    /**
     * Метод добавляет новый елемент в коллекцию перед указанным индексом
     * @param list колекция, в которую осуществляется вставка
     * @param index индекс, перед которым осуществляется вставка
     * @param value значение, которое вставляется в коллекцию
     * @param <T> тип объектов
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод добавляет новый елемент в коллекцию после указанного  индекса
     * @param list колекция, в которую осуществляется вставка
     * @param index индекс, после которого осуществляется вставка
     * @param value значение, которое вставляется в коллекцию
     * @param <T> тип объектов
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (iterator.nextIndex() == index + 1) {
                iterator.add(value);
                break;
            }
        }
    }

    /**
     * Метод удаляет элемент из коллекции если он соответствует предикату
     * @param list коллекция из которой удаляется элемент
     * @param filter предикат, задает условие, которому должен соответствовать удаляемый элемент
     * @param <T> тип объектов
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет текущий элемент коллекции по предикату  на новый элемент
     * @param list коллекция в которой осуществляется замена элемента
     * @param filter предикат, задает условие, которому должен соответствовать удаляемый элемент
     * @param value значение, которое вставляется в коллекцыю взамен удаляемого значения
     * @param <T> тип объектов
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из коллекции list все элементы, которые совпадают с элементами из коллекции elements
     * @param list коллекция из которой осуществляется удаление элементов
     * @param elements коллекция, с элементами, которые необходимо удалить из коллекции list
     * @param <T> тип объектов
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
}