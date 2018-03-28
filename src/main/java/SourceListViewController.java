import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SourceListViewController {
    private Main mainWindow;
    @FXML
    private TableView<Source> sourceTable = new TableView<>();
    @FXML
    private TableColumn<Source, String> titleColumn;
    @FXML
    private TableColumn<Source, String> authorColumn;
    @FXML
    private TableColumn<Source, String> mediumColumn;
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
    TextArea annotationField;
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
    @FXML
    private ContextMenu rightClickMenu;
    @FXML
    private MenuItem deleteItem;

    private ObservableList<Source> testList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setText("Author");

        mediumColumn.setCellValueFactory(cellData -> cellData.getValue().mediumProperty());
        mediumColumn.setText("Medium");

        sourceTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSource(newValue)
        );

        rightClickMenu = new ContextMenu();
        deleteItem = new MenuItem("Delete Source");

        deleteItem.setOnAction((ActionEvent event) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                sourceTable.getItems().remove(selectedIndex);
            }
        });

        titleField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setTitle(newValue);
            }
        });

        authorField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setAuthor(newValue);
            }
        });

        magazineTitleField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setMagazineTitle(newValue);
            }
        });

        websiteTitleField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setWebsiteTitle(newValue);
            }
        });

        volumeField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setVolume(newValue);
            }
        });

        editionField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setEdition(newValue);
            }
        });

        issueField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setIssue(newValue);
            }
        });

        publisherField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setPublisher(newValue);
            }
        });

        yearPublishedField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setYearPublished(newValue);
            }
        });

        datePublishedField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setDatePublished(newValue);
            }
        });

        URLfield.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setURL(newValue);
            }
        });

        versionField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setVersion(newValue);
            }
        });

        annotationField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setAnnotation(newValue);
            }
        });

        databaseField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setDatabase(newValue);
            }
        });

        mediumField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setMedium(newValue);
            }
        });

        pagesCitedField1.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setPagesCitedStart(newValue);
            }
        });

        pagesCitedField2.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setPagesCitedEnd(newValue);
            }
        });

        rightClickMenu.getItems().add(deleteItem);
        sourceTable.setContextMenu(rightClickMenu);

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

        Source source1 = new Source("How to not fall behind schdule");
        source1.setAuthor("Not Group 4");
        source1.setYearPublished("2018");
        source1.setMedium("Book");

        Source source2 = new Source("Writing Code Gud");
        source2.setAuthor("Still Not Group 4");
        source2.setURL("wkrp.xyz/notgroup4");
        source2.setMedium("Web");

        Source source3 = new Source("Accomplishing Thigs On Time");
        source3.setAuthor("Some Group That Is On Time");
        source3.setMedium("Smoke Signal");
        source3.setYearPublished("1000");

        testList.add(source1);
        testList.add(source2);
        testList.add(source3);


    }

    public void deleteSelected(ActionEvent actionEvent) {
        try {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                sourceTable.getItems().remove(selectedIndex);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void createNewSource(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddSourceView.fxml"));

            AnchorPane rootLayout = (AnchorPane) loader.load();

            primaryStage.setTitle("New Source");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            AddSourceController controller = loader.getController();
            controller.setMainWindow(this.mainWindow);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
