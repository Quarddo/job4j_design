package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод добавляет новый элемент в дерево.
     * Метод add - Должен находить узел по значению parent и добавлять в него
     * дочерний узел со значением child. В этом методе нужно проверить,
     * что значения child еще нет в дереве а parent есть. Если child есть,
     * то метод должен вернуть false.
     * @param parent елемент родитель, к которому прикрепляется елемент children
     * @param child элемент потомок, который прикрепляется к элементу родителю
     * @return true если добавление выполнено успешно
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParent = findBy(parent);
        Optional<Node<E>> nodeChild = findBy(child);
        if (nodeChild.isEmpty()
                && nodeParent.isPresent()) {
            nodeParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод производит поиско в дереве по элементу value
     * @param value элемент по которому производится поиск
     * @return возвращает value если данный элемент присутствует в дереве, в противном случае возвращает null
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
