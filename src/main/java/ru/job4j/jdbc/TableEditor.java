package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("org.postgresql.Driver"));
        String url = properties.getProperty("jdbc.url");
        String login = properties.getProperty("jdbc.login");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void statement(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s();", tableName);
        statement(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table if exist %s;", tableName);
        statement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add column %s %s;", tableName, columnName, type);
        statement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName);
        statement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("app.properties", StandardCharsets.UTF_8))) {
            Properties properties = new Properties();
            String tableName = "Table";
            String columnName = "People";
            String newColumnName = "Cars";
            properties.load(reader);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                tableEditor.createTable(tableName);
                System.out.println("Создание таблицы");
                tableEditor.dropTable(tableName);
                System.out.println("Удаление таблицы");
                tableEditor.addColumn(tableName, columnName, "varchar(255");
                System.out.println("Добавление столбца");
                tableEditor.dropColumn(tableName, columnName);
                System.out.println("Удаление столбца");
                tableEditor.renameColumn(tableName, columnName, newColumnName);
                System.out.println("Переименование столбца");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}