import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;



public class AdvancedSearchWindowController {

    private ObservableList<Source> testList = FXCollections.observableArrayList();
    private Main mainWindow;
    @FXML
    private TableView<Source> searchTable = new TableView<>();
    @FXML
    private TableColumn<Source, String> titleColumn;
    @FXML
    private TableColumn<Source, String> authorColumn;
    @FXML
    private TableColumn<Source, String> magazineTitleColumn;
    @FXML
    private TableColumn<Source, String> websiteTitleColumn;
    @FXML
    private TableColumn<Source, String> volumeColumn;
    @FXML
    private TableColumn<Source, String> editionColumn;
    @FXML
    private TableColumn<Source, String> issueColumn;
    @FXML
    private TableColumn<Source, String> yearPublishedColumn;
    @FXML
    private TableColumn<Source, String> datePublishedColumn;
    @FXML
    private TableColumn<Source, String> urlColumn;
    @FXML
    private TableColumn<Source, String> versionColumn;
    @FXML
    private TableColumn<Source, String> annotationColumn;
    @FXML
    private TableColumn<Source, String> mediumColumn;
    @FXML
    TextField titleField;
    @FXML
    TextField authorField;

    @FXML
    TextField yearPublishedField;
    @FXML
    TextField datePublishedField;

    @FXML
    TextField publisherField;


    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setText("Author");

        magazineTitleColumn.setCellValueFactory(cellData -> cellData.getValue().magazineTitleProperty());
        magazineTitleColumn.setText("Magazine Title");

        websiteTitleColumn.setCellValueFactory(cellData -> cellData.getValue().websiteTitleProperty());
        websiteTitleColumn.setText("Website Title");

        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
        volumeColumn.setText("Volume");

        editionColumn.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
        editionColumn.setText("Edition");

        issueColumn.setCellValueFactory(cellData -> cellData.getValue().issueProperty());
        issueColumn.setText("Issued");

        yearPublishedColumn.setCellValueFactory(cellData -> cellData.getValue().yearPublishedProperty());
        yearPublishedColumn.setText("Year Published");

        datePublishedColumn.setCellValueFactory(cellData -> cellData.getValue().datePublishedProperty());
        datePublishedColumn.setText("Date Published");

        urlColumn.setCellValueFactory(cellData -> cellData.getValue().URLProperty());
        urlColumn.setText("URL");

        versionColumn.setCellValueFactory(cellData -> cellData.getValue().versionProperty());
        versionColumn.setText("Version");

        annotationColumn.setCellValueFactory(cellData -> cellData.getValue().annotationProperty());
        annotationColumn.setText("Annotation");

        mediumColumn.setCellValueFactory(cellData -> cellData.getValue().mediumProperty());
        mediumColumn.setText("Medium");

    }


    @FXML
    private void clearSearch(ActionEvent actionEvent) {
        titleField.setText("");
        authorField.setText("");
        yearPublishedField.setText("");
        datePublishedField.setText("");
        publisherField.setText("");
        testList.removeAll();
        searchTable.refresh();


    }

    @FXML
    private void addToSourceList (ActionEvent actionEvent)
    {

        ObservableList<Source> getSourceResult = FXCollections.observableArrayList();
        getSourceResult =  searchTable.getSelectionModel().getSelectedItems();

        if (getSourceResult.size()>0) {
            Source sourceAdded = new Source("");

            sourceAdded.setTitle(getSourceResult.get(0).getTitle());

            sourceAdded.setAuthor(getSourceResult.get(0).getAuthor());

            sourceAdded.setMagazineTitle(getSourceResult.get(0).getMagazineTitle());


            sourceAdded.setWebsiteTitle(getSourceResult.get(0).getWebsiteTitle());

            sourceAdded.setVolume(getSourceResult.get(0).getVolume());

            sourceAdded.setEdition(getSourceResult.get(0).getEdition());


            sourceAdded.setIssue(getSourceResult.get(0).getIssue());

            sourceAdded.setPublisher(getSourceResult.get(0).getPublisher());

            sourceAdded.setYearPublished(getSourceResult.get(0).getYearPublished());

            sourceAdded.setDatePublished(getSourceResult.get(0).getDatePublished());

            sourceAdded.setURL(getSourceResult.get(0).getURL());

            sourceAdded.setVersion(getSourceResult.get(0).getVersion());

            sourceAdded.setDatabase(getSourceResult.get(0).getDatabase());

            sourceAdded.setDatabaseService(getSourceResult.get(0).getDatabaseService());

            sourceAdded.setMedium(getSourceResult.get(0).getMedium());

            sourceAdded.setAnnotation(getSourceResult.get(0).getAnnotation());

            sourceAdded.setPagesCitedStart(getSourceResult.get(0).getPagesCitedStart());

            sourceAdded.setPagesCitedEnd(getSourceResult.get(0).getPagesCitedEnd());


            mainWindow.addSource(sourceAdded);
        }
    }

    @FXML
    private void searchServer(ActionEvent actionEvent){

        // Server search will go here. Testing with some data for now

                Source source1 = new Source("This will be a Result");
                source1.setAuthor("NA");
                source1.setYearPublished("2018");
                source1.setMedium("Test");

                Source source2 = new Source("Another Title Result");
                source2.setAuthor("NA Again");
                source2.setURL("wkrp.xyz/notgroup4");
                source2.setMedium("Web");

                Source source3 = new Source("This will be Title Result 3");
                source3.setAuthor("NA");
                source3.setMedium("Test");
                source3.setYearPublished("1000");

                testList.add(source1);
                testList.add(source2);
                testList.add(source3);
                searchTable.setItems(testList);

    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void ExitSearch(ActionEvent actionEvent){
        titleField.getScene().getWindow().hide();

    }

}
