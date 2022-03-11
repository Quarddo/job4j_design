package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;


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
        Optional<Node<E>> nodeParent = findBy(parent);
        Optional<Node<E>> nodeChild = findBy(child);
        boolean rsl = nodeChild.isEmpty() && nodeParent.isPresent();
        if (rsl) {
            nodeParent.get().children.add(new Node<>(child));
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
        return findByPredicate(n -> n.value.equals(value));
    }

    /**
     * Метод производит проверку, является ли дерево бинарным.
     * если количество дочерних(children) узлов > 2, то дерево не бинарное
     * @return true если дерево бинароне.
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(n -> n.children.size() > 2).isEmpty();
    }

    /**
     * Метод осуществляет перебр всех елементов дерева, и осуществления проверки Predicate condition
     * @param condition задает условие с проверкой true / false
     * @return возвращает rsl элемент соответствующий условию condition
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}