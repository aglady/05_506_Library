package ru.gr05506.ui;

public class LibraryController {
    private final LibraryService service;
    private LibraryView view;

    public LibraryController(LibraryService service){ this.service = service; }

    public void attachView(LibraryView view){
        this.view = view;
        view.showBooks(service.getAllBooks()); // Для показа сохраненных книг
    }

    public void addBookClicked(){
        try{
            var author = view.getAuthor();
            var title = view.getBookTitle();
            var year = view.getYear();
            var info = view.getInfo();
            service.addBook(author, title, year, info);
            view.clearInput();
            view.showBooks(service.getAllBooks());
        } catch (IllegalArgumentException ex){
            view.showError(ex.getMessage());
        }
    }
}
