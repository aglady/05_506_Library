package ru.gr05506.ui.repository;

import ru.gr05506.ui.Book;

import java.util.List;

public interface LibraryRepository {
    void save(Book book);
    List<Book> findAll();
}
