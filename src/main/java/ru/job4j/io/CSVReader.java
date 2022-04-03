package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private final Path path;
    private final String delimiter;
    private final String out;
    private final String[] filter;

    public CSVReader(ArgsName args) {
        path = Paths.get(args.get("path"));
        delimiter = args.get("delimiter");
        out = args.get("out");
        filter = args.get("filter").split(",");
    }

    private void validate(ArgsName argsName) {
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("The given delimiter does not match the pattern.");
        }

        if (!path.toFile().exists() || !path.toFile().isFile()) {
            throw new IllegalArgumentException("File doesn't exist or File is not file.");
        }
    }

    public void handle(ArgsName argsName) {
        CSVReader csvReader = new CSVReader(argsName);
        csvReader.validate(argsName);
        try (Scanner scanner = new Scanner(new FileInputStream(path.toFile()), StandardCharsets.UTF_8)) {
            scanner.useDelimiter(";");
            List<String> listWriter = new ArrayList<>();
            List<Integer> listIndex = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                if (listIndex.isEmpty()) {
                    listIndex = retrieveColumnsIndices(line, filter);
                }
                String concatString = concatStr(line, listIndex);
                listWriter.add(concatString);
            }
            outPutData(listWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> retrieveColumnsIndices(String[] splitString, String[] columns) {
        List<Integer> indexList = new ArrayList<>();
        for (String colName : columns) {
            int n = Arrays.asList(splitString).indexOf(colName);
            if (n != -1) {
                indexList.add(n);
            }
        }
        return indexList;
    }

    private void outPutData(List<String> listWriter) {
        if ("stdout".equals(out)) {
            listWriter.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                listWriter.forEach(s -> pw.println(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String concatStr(String[] splitString, List<Integer> indexList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            if (i == indexList.size() - 1) {
                str.append(splitString[index]);
            } else {
                str.append(splitString[index]).append(";");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader(argsName);
        csvReader.handle(argsName);
    }
}
