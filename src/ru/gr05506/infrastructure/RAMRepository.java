package ru.gr05506.infrastructure;

import ru.gr05506.domain.Book;
import ru.gr05506.domain.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class RAMRepository implements BookRepository {
    private List<Book> books = new ArrayList<>();
    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAll() {
        return List.copyOf(books);
    }
}
