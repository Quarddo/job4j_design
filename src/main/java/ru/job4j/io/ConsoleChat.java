package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

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

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

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
