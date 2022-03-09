package ru.job4j.map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void whenPutTrue() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertTrue(table.put("true", 1));
    }

    @Test
    public void whenPutFalse() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertTrue(table.put("true", 1));
        assertFalse(table.put("true", 1));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        assertNull(table.get("one"));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        table.put("two", 2);
        assertThat(table.get("one"), is(1));
    }

    @Test
    public void whenGetForKey() {
        SimpleMap<String, Integer> table = new SimpleMap<>();
        table.put("one", 1);
        table.put("two", 2);
        int key = table.get("two");
        assertThat(key, is(2));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, String> table = new SimpleMap<>();
        table.put(1, "one");
        table.put(2, "two");
        assertTrue(table.remove(1));
    }

    @Test
    public void whenNotRemove() {
        SimpleMap<Integer, String> table = new SimpleMap<>();
        assertFalse(table.remove(1));
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<Integer, String> table = new SimpleMap<>();
        table.put(1, "one");
        table.put(2, "two");
        Iterator<Integer> iterator = table.iterator();
        assertThat(iterator.next(), is(1));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenEmptyIteratorThenException() {
        Map<String, Integer> table = new SimpleMap<>();
        Iterator<String> iterator = table.iterator();
        iterator.next();
    }

}