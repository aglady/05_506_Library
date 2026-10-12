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
            view.render(BookFormState.init());
        } catch (IllegalArgumentException e) {
            view.render(BookFormState.setError(e.getMessage()));
        }

    }
    public void inputChanged(){
        if (isAddAllowed()){
            view.render(BookFormState.setReady());
        }
        else{
            view.render(BookFormState.init());
        }
    }

    private boolean isAddAllowed() {
        return isNotBlank(view.getAuthor())
                && isNotBlank(view.getBookTitle())
                && isNotBlank(view.getYear());
    }

    private boolean isNotBlank(String value) {
        return  value != null && !value.isBlank();
    }

    public void viewOpened(){
        view.showBooks(service.getAllBooks());
        view.render(BookFormState.init());

    }
}
