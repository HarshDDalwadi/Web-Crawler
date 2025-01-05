package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {
    static Connection connection = null;
    Indexer(Document document, String url) {
        //Select important elements of document
        String title = document.title();
//        String link = url;
        String text = document.text();

        try{
            connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PAGES VALUES(?, ?, ?);");
            ps.setString(1, title);
            ps.setString(2, url);
            ps.setString(3, text);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
