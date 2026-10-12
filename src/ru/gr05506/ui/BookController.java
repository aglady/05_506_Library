package ru.gr05506.ui;

public class BookController {
    private final BookService service;
    private BookView view;

    public BookController(BookService service) {
        this.service = service;
    }

    public void attachView(BookView view) {this.view = view;}

    public void addBookClicked(){
        try {
            var author = view.getAuthor();
            var title = view.getBookTitle();
            var year = view.getYear();
            var info = view.getInfo();
            service.addBook(author,title,year,info);
            view.clearInput();
            view.showBooks(service.getAllBooks());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }

    }
}
