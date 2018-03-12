import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOfSourcesController {
    private Main mainWindow;
    @FXML
    private TableView<Source> sourceTable = new TableView<>();
    @FXML
    private TableColumn<Source, String> titleColumn;
    @FXML
    private TableColumn<Source, String> databaseColumn;

    private ObservableList<Source> testList = FXCollections.observableArrayList();

    public ListOfSourcesController() {

    }

    @FXML
    private void initialize() {

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        databaseColumn.setCellValueFactory(cellData -> cellData.getValue().databaseProperty());
        databaseColumn.setText("Database");

    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;

        testList = mainWindow.getSourceList();
        testList.add(new Source("Ping"));
        sourceTable.setItems(testList);
    }
}
