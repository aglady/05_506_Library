package ru.gr05506.ui;

import java.time.LocalDateTime;

public class BookInputParser {
    public ParsedBookInput parse(String author, String title,
                 String year, String info){
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Поле Автор не может быть пустым!");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле Название не может быть пустым!");
        }

        int iYear = getYear(year);

        return new ParsedBookInput( author, title, iYear, info);

    }

    private int getYear(String sYear) {
        if ( sYear == null || sYear.isBlank()) {
            throw new IllegalArgumentException("Поле Год не может быть пустым!");
        }
        int iYear;
        try {
            iYear = Integer.parseInt(sYear);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Год не является числом",e);
        }

        return iYear;
    }

}
