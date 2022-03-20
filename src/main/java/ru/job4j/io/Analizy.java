package ru.job4j.io;

import ru.job4j.collection.List;
import java.io.*;
import java.util.ArrayList;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean serverActivity = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String line = reader.readLine();
            while (line != null && line.contains("400") || line.contains("500")) {
                String[] rsl = line.split(" ", 2);
                serverActivity = true;
                writer.print("Сервер не работал с " + rsl[1]);
                if (line.contains("200") || line.contains("300")) {
                    serverActivity = false;
                    writer.print("по " + rsl[1]);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Analizy().unavailable("server.log", ".server/unavailable.txt");
    }
}
