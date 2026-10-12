package ru.gr05506.ui;

import java.util.List;

public interface BookView {
    String getAuthor();
    String getBookTitle();
    String getYear();
    String getInfo();

    void clearInput();
    void showBooks(List<Book> books);
    void showError(String message);
    void render(BookFormState state);
}


