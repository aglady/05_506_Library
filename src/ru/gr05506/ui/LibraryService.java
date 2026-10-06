package ru.gr05506.ui;

import ru.gr05506.ui.repository.LibraryRepository;

import java.time.LocalDateTime;
import java.util.List;

public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void addBook(String author, String title, String year, String info){
        if (author == null || author.isBlank()){
            throw new IllegalArgumentException("Введите автора");
        }
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Введите название");
        }
        if (year == null || year.isBlank()){
            throw new IllegalArgumentException("Введите год издания");
        }

        int iYear = getYear(year);
        var book = new Book(author, title, iYear, info);
        repository.save(book);
    }

    private int getYear(String year){
        int iYear;
        try {
            iYear = Integer.parseInt(year);
        } catch (NumberFormatException ex){
            throw new IllegalArgumentException("Некорректный год", ex);
        }
        if(iYear > LocalDateTime.now().getYear()){
            throw new IllegalArgumentException("Некорректный год");
        }
        return iYear;
    }

    public List<Book> getAllBooks() { return repository.findAll(); }
}
