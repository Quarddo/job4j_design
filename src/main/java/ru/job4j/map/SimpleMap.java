package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * проверяем наличие свободного места для вставки
     * если места недостаточно, уыеличиваем размер таблицы с помощью метода expand()
     * генерируем хэш ключа и вычисляем индекс элемента
     * проверяем занят ли данный индекс в таблице
     * @param key уникальный ключ SimpleMap
     * @param value значение, в паре key, value
     * @return если индекс свободен, добавляем и возвращаем true,
     *         в противном случае false
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод создает хеш-значение для ключа, который добавлется в колекцию
     * @return возвращает хеш-значение
     */
    private int hash(int hashCode) {
        return hashCode ^ hashCode >> 16;
    }

    /**
     * Метод формирует индес ключа, по которому объект будет добавляться в массив  с уникальными ключами
     * @param hash хэш-значение, из которого формируется индекс
     * @return индекс для массива
     */
    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    /**
     * Метод увеличивает массив если он, равняется или превышает 0.75 от общего размера.
     * Содержимое старого массива вставляет в новый расширенный массив и копирует новый массив
     */
    private void expand() {
            capacity *= 2;
            table = Arrays.copyOf(table, capacity);
            MapEntry<K, V>[] buket = new MapEntry[capacity];
            for (int i = 0; i < capacity - 1; i++) {
                if (table[i] == null) {
                    K key = table[i].key;
                    int index = indexFor(hash(key.hashCode()));
                    if (buket[index] == null) {
                        buket[index] = table[i];
                    }
                }
                table = Arrays.copyOf(buket, capacity);
            }
    }

    /**
     * Метод получает value по key, возвращает значение если value найден
     * @param key  ключ, по которому находим значение
     * @return возвращает null, если значение не найдено
     */
    @Override
    public V get(K key) {
        V value = null;
        int index = indexFor(key.hashCode());
        if (table[index] != null) {
            value = table[index].value;
        }
        return value;
    }

    /**
     * Метод удалеет значени массива по ключу
     * @param key ключ, по которому удалеется значение
     * @return возвращает true, если значение удалено и false в противном случае
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = hash(key.hashCode());
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;

        }
        return rsl;
    }

    /**
     * Метод переберает элементы массива
     * @return возвращает значения массива по порядку
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (modCount != expectModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
