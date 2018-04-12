import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SourceListViewController {
    private Main mainWindow;
    @FXML
    private TableView<Source> sourceTable = new TableView<>();
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
    TextField searchField;
    @FXML
    private ContextMenu rightClickMenu;
    @FXML
    private MenuItem deleteItem;

    String serverURL = "http://vpn.lucidlynx.net:9200/library/_search";
    List<Source> quickResults = new ArrayList<Source>();

    ContextMenu autocompleteMenu;

    private ObservableList<Source> testList = FXCollections.observableArrayList();

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

        sourceTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSource(newValue)
        );

        rightClickMenu = new ContextMenu();
        deleteItem = new MenuItem("Delete Source");

        autocompleteMenu = new ContextMenu();
        MenuItem menu1 = new MenuItem("");
        MenuItem menu2 = new MenuItem("");
        MenuItem menu3 = new MenuItem("");
        MenuItem menu4 = new MenuItem("Advanced Search...");
        autocompleteMenu.getItems().addAll(menu4);

        menu4.setOnAction((ActionEvent e) -> advancedSearch(e));

        menu1.setOnAction((ActionEvent e) -> {
            mainWindow.sourceList.add(quickResults.get(0));
            autocompleteMenu.hide();
            searchField.setText("");
            quickResults.clear();
        });

        menu2.setOnAction((ActionEvent e) -> {
            mainWindow.sourceList.add(quickResults.get(1));
            autocompleteMenu.hide();
            searchField.setText("");
            quickResults.clear();
        });

        menu3.setOnAction((ActionEvent e) -> {
            mainWindow.sourceList.add(quickResults.get(2));
            autocompleteMenu.hide();
            searchField.setText("");
            quickResults.clear();
        });

        searchField.setOnKeyTyped((KeyEvent e) ->{
            if(!searchField.getText().equals("")){
                //populate autocompleteMenu with proper results through communication with unirest
                try {
                    HttpResponse<JsonNode> jsonResponse = Unirest.get(serverURL)
                            .header("accept", "application/json")
                            .queryString("q", searchField.getText())
                            .asJson();
                    System.out.println("Search output:" + jsonResponse.getBody().toString());
                    quickResults.clear();
                    //convert json formatted return statement to Source objects, place in list
                }
                catch(Exception ex){
                    System.out.println("Failed to communicate with server :c");
                }
                if(quickResults.size()>=3){
                    menu1.setText(quickResults.get(0).title.get());
                    menu2.setText(quickResults.get(1).title.get());
                    menu3.setText(quickResults.get(2).title.get());
                    autocompleteMenu.getItems().clear();
                    autocompleteMenu.getItems().addAll(menu1, menu2, menu3, menu4);
                }
                else if(quickResults.size()>=2){
                    menu1.setText(quickResults.get(0).title.get());
                    menu2.setText(quickResults.get(1).title.get());
                    autocompleteMenu.getItems().clear();
                    autocompleteMenu.getItems().addAll(menu1, menu2, menu4);
                }
                else if(quickResults.size()>=1){
                    menu1.setText(quickResults.get(0).title.get());
                    autocompleteMenu.getItems().clear();
                    autocompleteMenu.getItems().addAll(menu1, menu4);
                }
                else{
                    autocompleteMenu.getItems().clear();
                    autocompleteMenu.getItems().addAll(menu4);
                }
                autocompleteMenu.show(searchField, Side.TOP, 0,0);
            }
            else{
                autocompleteMenu.hide();
            }
        });

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
        titleField.autosize();

        authorField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setAuthor(newValue);
            }
        });
        authorField.autosize();

        magazineTitleField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setMagazineTitle(newValue);
            }
        });
        magazineTitleField.autosize();

        websiteTitleField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setWebsiteTitle(newValue);
            }
        });
        websiteTitleField.autosize();

        volumeField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setVolume(newValue);
            }
        });
        volumeField.autosize();

        editionField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setEdition(newValue);
            }
        });
        editionField.autosize();

        issueField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setIssue(newValue);
            }
        });
        issueField.autosize();

        publisherField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setPublisher(newValue);
            }
        });
        publisherField.autosize();

        yearPublishedField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setYearPublished(newValue);
            }
        });
        yearPublishedField.autosize();

        datePublishedField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setDatePublished(newValue);
            }
        });
        datePublishedField.autosize();

        URLfield.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setURL(newValue);
            }
        });
        URLfield.autosize();

        versionField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setVersion(newValue);
            }
        });
        versionField.autosize();

        annotationField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setAnnotation(newValue);
            }
        });
        annotationField.autosize();

        databaseField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setDatabase(newValue);
            }
        });
        databaseField.autosize();

        mediumField.textProperty().addListener((obj, oldValue, newValue) -> {
            int selectedIndex = sourceTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                Source selectedSource = sourceTable.getSelectionModel().getSelectedItem();
                selectedSource.setMedium(newValue);
            }
        });
        mediumField.autosize();

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

    public void advancedSearch(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AdvancedSearchWindow.fxml"));

            AnchorPane rootLayout = (AnchorPane) loader.load();

            primaryStage.setTitle("Advanced Search");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            AdvancedSearchWindowController controller = loader.getController();
            controller.setMainWindow(this.mainWindow);


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void UpdateSearchResults(ActionEvent actionEvent){
        if(searchField.getText().equals("")){
            //clear associated results list
        }
        else{
            //send string to elasticsearch
            //use results to populate associated results list
        }
    }
}
