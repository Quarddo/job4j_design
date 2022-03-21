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
                    if (!serverActivity && ("400".equals(rsl[0]) || "500".equals(rsl[0]))) {
                        writer.print(rsl[1] + ";");
                        serverActivity = true;
                    }
                    if (serverActivity && ("200".equals(rsl[0]) || "300".equals(rsl[0]))) {
                        writer.println(rsl[1]);
                        serverActivity = false;
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        Analizy analysis = new Analizy();
        analysis.unavailable("server.log", "unavailable.csv");
    }
}
