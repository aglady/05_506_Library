package ru.gr05506.domain;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
}
