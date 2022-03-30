package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static String out;
    private static String filter;

    public CSVReader(ArgsName args) {
        path = Paths.get(args.get("path"));
        delimiter = args.get("delimiter");
        out = args.get("out");
        filter = args.get("filter");
    }

    public static void validate(ArgsName argsName) {
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("The given delimiter does not match the pattern.");
        }
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }
        if (!path.toFile().exists() || !path.toFile().isFile()) {
            throw new IllegalArgumentException("File doesn't exist or File is not file.");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        List<String[]> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path.toFile()), StandardCharsets.UTF_8)
                .useDelimiter(";" + System.lineSeparator())) {
            PrintWriter writer = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8));
            while (scanner.hasNext()) {
                list.add(scanner.nextLine().split(delimiter));
                for (String[] strings : list) {
                    writer.println(strings[0] + strings[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}