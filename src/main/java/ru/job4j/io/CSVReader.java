package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        if (!delimiter.equals(";")) {
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
        try (Scanner scanner = new Scanner(new FileInputStream(path.toFile()), StandardCharsets.UTF_8)) {
            scanner.useDelimiter(";");
            PrintWriter writer = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8));
            while (scanner.hasNext()) {
                list.add(scanner.nextLine().split(delimiter));
            }
            List<String> filters = Arrays.asList(filter.split(","));
            for (String[] strings : list) {
                StringJoiner stringJoiner = new StringJoiner(delimiter);
                for (int i = 0; i < strings.length; i++) {
                    if (filters.contains(list.get(0)[i])) {
                        stringJoiner.add(strings[i]);
                    } else {
                        stringJoiner.add(strings[i]).add(";");
                    }
                    writer.println(stringJoiner);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/maksimteriushov/projects/job4j_design/source.csv");
        File target = new File("/Users/maksimteriushov/projects/job4j_design/data/target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        handle(argsName);
    }
}