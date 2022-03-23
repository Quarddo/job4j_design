package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> notDuplicate = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProper = new FileProperty(attrs.size(), file.toFile().getName());
        if (!notDuplicate.containsKey(fileProper)) {
            notDuplicate.put(fileProper, new ArrayList<>());
        }
        notDuplicate.get(fileProper).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printDuplicate() {
        notDuplicate.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .forEach(System.out::println);
    }
}
