package ru.gr05506.domain;

public record Book (String author, String title, int year, String info)
{
    public Book{
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Поле Автор не может быть пустым!");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле Название не может быть пустым!");
        }

        if (year == 0){
            throw new IllegalArgumentException("Год не может быть нулевым");
        }


    }


}
