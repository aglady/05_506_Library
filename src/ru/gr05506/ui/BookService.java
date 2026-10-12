package ru.gr05506.ui;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(String author, String title,
                        String year, String info) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Поле Автор не может быть пустым!");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле Название не может быть пустым!");
        }

        if (year == null || year.isBlank()) {
            throw new IllegalArgumentException("Поле Год не может быть пустым!");
        }
        int iYear = getYear(year);

        Book book = new Book(author,title,iYear,info);
        repository.save(book);
    }
    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    private int getYear(String sYear) {
       int iYear;
       try {
           iYear = Integer.parseInt(sYear);
       } catch (NumberFormatException e) {
           throw new IllegalArgumentException("Год не является числом",e);
       }
       if (iYear > LocalDateTime.now().getYear())
           throw new IllegalArgumentException("Некорректный год");

       return iYear;
    }
}
