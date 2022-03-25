package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
       try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
           for (Path source : sources) {
               zip.putNextEntry(new ZipEntry(source.toString()));
               try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toString()))) {
                   zip.write(in.readAllBytes());
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("Check the number of entered arguments (there should be three)");
        }
        Path directory = Path.of(argsName.get("d"));
        if (!directory.toFile().exists() || !directory.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist or is not a directory.");
        }
        String excludeFiles = argsName.get("e");
        if (!excludeFiles.startsWith(".")) {
            throw new IllegalArgumentException("The parameter key does not match the format.");
        }
        List<Path> filter = Search.search(directory, p -> !p.toFile().getName()
                .endsWith(excludeFiles));
        File outPut = new File(argsName.get("o"));
        Zip zip = new Zip();
        zip.packFiles(filter, outPut);
    }
}
