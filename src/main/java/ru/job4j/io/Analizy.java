package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
            boolean serverActivity = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(source));
                 PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] rsl = line.split(" ", 2);
                    if (!serverActivity && (rsl[0].equals("400") || rsl[0].equals("500"))) {
                        writer.print("Сервер не работал с " + rsl[1]);
                        serverActivity = true;
                    }
                    if (serverActivity && (rsl[0].equals("200") || rsl[0].equals("300"))) {
                        writer.print("по " + rsl[1]);
                        serverActivity = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        Analizy analysis = new Analizy();
        analysis.unavailable(".server/server.txt", ".server/unavailable.txt");
    }
}
