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
    List<Path> duplicate = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProper = new FileProperty(attrs.size(), file.toFile().getName());
        if (notDuplicate.containsKey(fileProper)) {
            System.out.println("Дубликат: " + file.toAbsolutePath());
        } else {
            duplicate.add(file);
            notDuplicate.put(fileProper, duplicate);
        }
        return super.visitFile(file, attrs);
    }
}
