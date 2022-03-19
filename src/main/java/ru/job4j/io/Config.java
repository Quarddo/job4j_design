package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод добавляет инфорвацию с файла .properties в config -> key, value
     * метод игнорирует комментарии
     * метод игнорирует пустые строки
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String rslLine = read.readLine();
            while (rslLine != null) {
                if (!rslLine.isEmpty() && !rslLine.contains("#")
                        && rslLine.contains("=")) {
                    String[] strings = rslLine.split("=", 2);
                    if (strings[0].isEmpty() || strings[1].isEmpty()) {
                        throw new IllegalArgumentException("Нарушен шаблон файла.");
                    }
                    values.put(strings[0], strings[1]);
                }
                rslLine = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key ключ по которому возвращается значение
     * @return Метод возвращает значение ключа
     */
    public String value(String key) {
        return values.get(key);
    }

    /**
     * Переопределенный метод, выводит на экран содержимоее config
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}