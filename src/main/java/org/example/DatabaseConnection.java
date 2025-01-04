package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;
    public Connection getConnection() {
        if(connection != null) {
            return connection;
        }

        String user = "root";
        String password = "Harsh2003+";
        String db = "searchEngineApp";
        return getConnection(user, password, db);
    }

    private Connection getConnection(String user, String password, String db) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return connection;
    }
}
