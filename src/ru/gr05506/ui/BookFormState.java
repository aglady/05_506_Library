package ru.gr05506.ui;

public record BookFormState(
        boolean addEnabled,
        String status,
        String error
) {
    public static BookFormState init(){
        return new BookFormState(false, "Заполните форму", null );
    }

    public static BookFormState setReady(){
        return new BookFormState(true, "Можно добавить", null );
    }

    public static BookFormState setError(String message){
        return new BookFormState(false, "О Ш И Б К А", message );
    }


}
