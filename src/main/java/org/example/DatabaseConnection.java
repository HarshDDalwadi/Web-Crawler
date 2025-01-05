package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/searchEnginApp";
    private static final String USER = "root";
    private static final String PASSWORD = "Harsh2003+";

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    } catch (SQLException e) {
                        System.err.println("Error while establishing the connection:");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
