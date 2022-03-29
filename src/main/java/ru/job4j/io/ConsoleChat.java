package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

/**
 * 'Консольный чат'. Описание:
 * пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * если пользователь вводит слово:
 * - «стоп» программа замолкает, при этом он может продолжать отправлять сообщения в чат.
 * - «продолжить» программа снова начинает отвечать.
 * - «закончить» программа прекращает работу.
 * запись диалога в текстовый лог включает слова-команды стоп/продолжить/закончить.
 */

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    /**
     * класс принимает в конструктор 2 параметра
     * path - имя файла, в который будет записан весь диалог между ботом и пользователем,
     * botAnswers - имя файла в котором находятся строки с ответами,
     * которые будет использовать бот
     */

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * содержит логику чата;
     */
    public void run() {
        List<String> log = new ArrayList<>();
        List<String> listAnswers = readPhrases();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            log.add(line);
            boolean botAction = true;
            while (!OUT.equals(line)) {
                if (STOP.equals(line)) {
                    botAction = false;
                }
                if (CONTINUE.equals(line)) {
                    botAction = true;
                }
                if (botAction) {
                    String answer = listAnswers.get(new Random().nextInt(listAnswers.size()));
                    System.out.println(answer);
                    log.add(answer);
                }
                line = reader.readLine();
                log.add(line);
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * читает фразы из файла;
     *
     * @return список ответов
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * сохраняет лог чата в файл
     */
    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("/Users/maksimteriushov/projects/job4j_design/log.txt",
                "/Users/maksimteriushov/projects/job4j_design/botAnswers.txt");
        cc.run();
    }
}
