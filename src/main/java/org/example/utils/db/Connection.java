package org.example.utils.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// FIXME: 04.11.2023 Сделать через конфиг
public class Connection {
    private final String url = "jdbc:postgresql://localhost:5432/school";
    private final String user = "postgres";
    private final String password = "admin";

    private final Statement connection;

    public Connection() {
        this.connection = createConnection();
    }

    public Statement createConnection() {
        try {
            return DriverManager.getConnection(url, user, password).createStatement();
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных");
            throw new RuntimeException(e);
        }
    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
