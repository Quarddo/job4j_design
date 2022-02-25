package ru.job4j.collection.list;

import java.util.*;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    transient int size = 0;
    private transient Node<E> first;
    private transient Node<E> last;
    private transient int modCount = 0;

    private static class Node<E> {
        E item;
        Node<E> nextItem;
        Node<E> prevItem;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.nextItem = next;
            this.prevItem = prev;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.nextItem = newNode;
        }
        size++;
        modCount++;

    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.nextItem;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            int i = 0;
            Node<E> forth = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return forth != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = forth.item;
                forth = forth.nextItem;
                return rsl;
            }

        };
    }
}