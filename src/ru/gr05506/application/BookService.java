package ru.gr05506.application;

import ru.gr05506.domain.Book;
import ru.gr05506.domain.BookRepository;
import ru.gr05506.ui.ParsedBookInput;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(ParsedBookInput parsedBook) {

        int iYear = parsedBook.year();
        if (iYear > LocalDateTime.now().getYear())
            throw new IllegalArgumentException("Некорректный год");

        Book book = new Book(parsedBook.author(),parsedBook.title(),
                iYear, parsedBook.info());
        repository.save(book);
    }
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

}
