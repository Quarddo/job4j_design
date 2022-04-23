/**package ru.job4j.io.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchByCriteria {

    public static void validate(ArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("The argument does not exist or is not a directory");
        }
    }

    public static void handle(ArgsName argsName) throws IOException {
        validate(argsName);
        Path startPath = Paths.get(argsName.get("d"));
        String typeSearch = argsName.get("t");
        String name = argsName.get("n");
        String out = argsName.get("o");
        List<Path> pathList = new ArrayList<>();
        if ("mask".contains(typeSearch)) {
            pathList = search(startPath, path -> path.toFile().getName()
                    .endsWith(name.replace("*", "")));
        }
        if ("name".contains(typeSearch)) {
            pathList = search(startPath, path -> path.toFile().getName()
                    .equals(name));
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            pathList.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
*/