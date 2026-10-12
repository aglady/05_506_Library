import ru.gr05506.ui.BookController;
import ru.gr05506.ui.BookService;
import ru.gr05506.ui.BookWindow;
import ru.gr05506.ui.RAMRepository;

void main() {

    RAMRepository repository = new RAMRepository();
    BookService service = new BookService(repository);
    BookController controller = new BookController(service);
    BookWindow wnd = new BookWindow(controller);
    controller.attachView(wnd);
    wnd.setVisible(true);
    controller.viewOpened();
}