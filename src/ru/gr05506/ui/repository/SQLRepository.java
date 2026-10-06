package ru.gr05506.ui.repository;

import ru.gr05506.ui.Book;

import javax.crypto.NoSuchPaddingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRepository implements LibraryRepository{
    private Connection connection;

    public SQLRepository(String url) throws SQLException{
        try {
            connection = DriverManager.getConnection(
                    url
            );

            try (Statement statement = connection.createStatement()){
                statement.executeUpdate("""
               CREATE TABLE IF NOT EXISTS library\s
                            ("author"	TEXT NOT NULL,
                         	"title"	TEXT NOT NULL,
                         	"year"	INTEGER NOT NULL,
                         	"info"	TEXT,
                         	PRIMARY KEY("title")
                         );
              \s""");
            }
        } catch (SQLException e) {
            throw new SQLException("No such database: " + url + " or sqlite library is not installed",e);
        }
    }

    @Override
    public void save(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
                """
                     INSERT OR REPLACE INTO library
                     (author, title, year, info)
                     VALUES (?, ?, ?, ?)
                     """
        )){
            statement.setString(1, book.author());
            statement.setString(2, book.title());
            statement.setInt(3, book.year());
            statement.setString(4, book.info());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT author, title, year, info FROM library")) {
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                String info = resultSet.getString("info");

                books.add(new Book(author,title,year,info));
            }

        } catch (Exception e) {
        }
        return books;
    }
}
