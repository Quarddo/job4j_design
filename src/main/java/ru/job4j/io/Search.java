package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;


public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        if (args.length != 2) {
            throw new IllegalArgumentException("Check the number of entered arguments (there should be two)");
        }
        if (!start.toFile().exists() || !start.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist or is not a directory.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The second parameter does not match the format.");
        }
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
