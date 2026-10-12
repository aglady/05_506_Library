package ru.gr05506.ui;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
}
