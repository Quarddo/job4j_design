package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    /**
     * Даный метод читает файл
     */
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            /**
             * Данная часть кода устанавливает условия вывода текста
             * каждый элемент начинается с новый строки
             * И проверяет четно чилсо или нет
             */
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                String num = Integer.parseInt(line) % 2 == 0
                        ? " - четное число" : " - нечетное число";
                System.out.println(line + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
