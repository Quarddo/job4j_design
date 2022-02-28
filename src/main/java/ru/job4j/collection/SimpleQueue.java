package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Проверка стоставляющих стеков и если они они пустые, то
     * выбрасывается исключение NoSuchElementException.
     * @return возвращает первое значение и удалает его из коллекции
     */
    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * отвечает за определение элемента в конец
     * @param value - значение элемента
     */
    public void push(T value) {
        in.push(value);
    }
}
