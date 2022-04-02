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
    private static String[] filter;

    public CSVReader(ArgsName args) {
        path = Paths.get(args.get("path"));
        delimiter = args.get("delimiter");
        out = args.get("out");
        filter = args.get("filter").split(",");
    }

    public void validate(ArgsName argsName) {
        CSVReader csvReader = new CSVReader(argsName);
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
                listWriter.add(concatString + System.lineSeparator());
            }
            outPutData(listWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> retrieveColumnsIndices(String[] splitString, String[] columns) {
        List<Integer> indexList = new ArrayList<>();
        for (String colName : columns) {
            int n = Arrays.asList(splitString).indexOf(colName);
            if (n != -1) {
                indexList.add(n);
            }
        }
        return indexList;
    }

    private static void outPutData(List<String> listWriter) {
        if ("stdout".equals(out)) {
            System.out.println(listWriter);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                pw.println(listWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String concatStr(String[] splitString, List<Integer> indexList) {
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

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
