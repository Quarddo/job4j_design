package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> notDuplicate = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProper = new FileProperty(attrs.size(), file.toFile().getName());
        if (notDuplicate.contains(fileProper)) {
            System.out.println("Дубликат: " + file.toAbsolutePath());
        } else {
            notDuplicate.add(fileProper);
        }
        return super.visitFile(file, attrs);
    }
}
