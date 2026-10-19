import ru.gr05506.ui.BookController;
import ru.gr05506.application.BookService;
import ru.gr05506.ui.BookInputParser;
import ru.gr05506.ui.BookWindow;
import ru.gr05506.infrastructure.RAMRepository;

void main() {

    RAMRepository repository = new RAMRepository();
    BookService service = new BookService(repository);
    BookInputParser parser = new BookInputParser();
    BookController controller = new BookController(service, parser);
    BookWindow wnd = new BookWindow(controller);
    controller.attachView(wnd);
    wnd.setVisible(true);
    controller.viewOpened();
}