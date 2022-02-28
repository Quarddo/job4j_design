package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * метод добавляет элемент в конец списка
     * @param value добавляемый элемент
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод добавлет элемент в начало списка
     * @param value элемент который добавляется
     */
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    /**
     * T value - значение которое будем удалять
     * Node<T> next - извлекаемый узел
     * Данный метод удаляет первый узел.
     * Если первое значение равно Null, значит список пуст и появится исключение
     * А если список не пуст, то мы обнуляем ссылки у удаляемого узла на
     * следующий узел и на хранимое значение,
     * так же мы меняем head (первый узел), на next (следующий узел).
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> next = head.next;
        T value = head.value;
        head.value = null;
        head.next = null;
        head = next;

        return value;
    }

    /**
     * метод проверяет пуст лист или нет
     * @return возвращает true если пустой, false если нет
     */
    public boolean isEmpty() {
        return head == null;
    }

    /** Даннный метод переворачивает связанный список
     * Провереем первый элемент и ссылку на следующий элемент, если они равны Null, то выбрасывается исключение
     * head - храниться ссылка на предыдущий элемент
     * current - это ссылка на текущий элемент
     * next - это ссылка на следующий элемент
     * Присваиваем последнему элементу первый
     * Создаем переменную, ссылку на текущий узел
     * Последний элемент списка не должен иметь ссылку на следущий элемент,поэтому присваиваем ему значение Null
     * Далее идем по списку, до тех пор пока текущий элемент(current) не равен Null.
     * В цикле мы сохраняем ссылку на следующий элемент
     * Ссылке на следующий элемент текущего элемента присваиваем
     * значение head( в данный момент в head находится предыдущий элемент)
     * После сдвинем все указатели на один элемент вперед
     */
    public boolean revert() {
        boolean rsl = false;
        if (head == null || head.next == null) {
            return rsl;
        }
        tail = head;
        Node<T> current = head.next;
        head.next = null;
        while (current != null) {
            Node<T> next = current.next;
            current.next = head;
            head = current;
            current = next;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
