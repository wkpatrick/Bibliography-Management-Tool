import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SourceListViewController {
    private Main mainWindow;
    @FXML
    private TableView<Source> sourceTable = new TableView<>();
    @FXML
    private TableColumn<Source, String> titleColumn;
    @FXML
    private TableColumn<Source, String> databaseColumn;
    @FXML
    TextField titleField;
    @FXML
    TextField authorField;
    @FXML
    TextField magazineTitleField;
    @FXML
    TextField websiteTitleField;
    @FXML
    TextField volumeField;
    @FXML
    TextField editionField;
    @FXML
    TextField issueField;
    @FXML
    TextField publisherField;
    @FXML
    TextField yearPublishedField;
    @FXML
    TextField datePublishedField;
    @FXML
    TextField URLfield;
    @FXML
    TextField versionField;
    @FXML
    TextField annotationField;
    @FXML
    TextField databaseField;
    @FXML
    TextField databaseServiceField;
    @FXML
    TextField mediumField;
    @FXML
    TextField pagesCitedField1;
    @FXML
    TextField pagesCitedField2;

    private ObservableList<Source> testList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        databaseColumn.setCellValueFactory(cellData -> cellData.getValue().databaseProperty());
        databaseColumn.setText("Database");

        sourceTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSource(newValue)
        );
    }

    public void showSource(Source source) {
        if (source != null) {
            titleField.setText(source.getTitle());
            authorField.setText(source.getAuthor());
            magazineTitleField.setText(source.getMagazineTitle());
            websiteTitleField.setText(source.getWebsiteTitle());
            volumeField.setText(source.getVolume());
            editionField.setText(source.getEdition());
            issueField.setText(source.getIssue());
            publisherField.setText(source.getPublisher());
            yearPublishedField.setText(source.getYearPublished());
            datePublishedField.setText(source.getDatePublished());
            URLfield.setText(source.getURL());
            versionField.setText(source.getVersion());
            annotationField.setText(source.getAnnotation());
            databaseField.setText(source.getDatabase());
            mediumField.setText(source.getMedium());
            pagesCitedField1.setText(source.getPagesCitedStart());
            pagesCitedField2.setText(source.getPagesCitedEnd());
        } else {
            titleField.setText("");
            authorField.setText("");
            magazineTitleField.setText("");
            websiteTitleField.setText("");
            volumeField.setText("");
            editionField.setText("");
            issueField.setText("");
            publisherField.setText("");
            yearPublishedField.setText("");
            datePublishedField.setText("");
            URLfield.setText("");
            versionField.setText("");
            annotationField.setText("");
            databaseField.setText("");
            mediumField.setText("");
            pagesCitedField1.setText("");
            pagesCitedField2.setText("");
        }
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;

        testList = mainWindow.getSourceList();

        sourceTable.setItems(testList);
    }

}
