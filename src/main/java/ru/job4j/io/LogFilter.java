package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный подход называется в потоках ввода вывода, назыввется буферизированные обертки
 * Базовый поток - это поток байтов. Его можно обернуть в символьный поток, если мы знаем,
 * что нам нужно читать текст.
 * Здесь тоже используется декоратор.
 * Символьные потоки позволяют читать сразу символы, а не байты.
 * Так же у буферизированного символьного потока есть методы чтения целой строки.
 */
public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines().filter(s -> s.contains(" 404 ")).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}
