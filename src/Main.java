import ru.gr05506.ui.LibraryController;
import ru.gr05506.ui.LibraryGUIWindow;
import ru.gr05506.ui.LibraryService;
import ru.gr05506.ui.repository.LibraryRepository;
import ru.gr05506.ui.repository.RAMRepository;
import ru.gr05506.ui.repository.SQLRepository;

import java.sql.SQLException;

void main() {
    LibraryRepository repository = null;
    try {
        repository = new SQLRepository("jdbc:sqlite:src/main/resources/data.db");
    } catch (SQLException e) {
        System.err.println(e.getMessage());
        repository = new RAMRepository();
    }
    LibraryService service = new LibraryService(repository);

    LibraryController controller = new LibraryController(service);

    LibraryGUIWindow wnd = new LibraryGUIWindow(controller);
    controller.attachView(wnd);
    wnd.setVisible(true);
}