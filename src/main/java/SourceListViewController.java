import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
    HBox titleBox;
    @FXML
    TextField authorField;
    @FXML
    HBox authorBox;
    @FXML
    TextField magazineTitleField;
    @FXML
    HBox magazineTitleBox;
    @FXML
    TextField websiteTitleField;
    @FXML
    HBox websiteTitleBox;
    @FXML
    TextField volumeField;
    @FXML
    HBox volumeBox;
    @FXML
    TextField editionField;
    @FXML
    HBox editionBox;
    @FXML
    TextField issueField;
    @FXML
    HBox issueBox;
    @FXML
    TextField publisherField;
    @FXML
    HBox publisherBox;
    @FXML
    TextField yearPublishedField;
    @FXML
    HBox yearPublishedBox;
    @FXML
    TextField datePublishedField;
    @FXML
    HBox datePublishedBox;
    @FXML
    TextField URLfield;
    @FXML
    HBox URLBox;
    @FXML
    TextField versionField;
    @FXML
    HBox versionBox;
    @FXML
    TextArea annotationField;
    @FXML
    HBox annotationBox;
    @FXML
    TextField databaseField;
    @FXML
    HBox databaseBox;
    @FXML
    TextField databaseServiceField;
    @FXML
    HBox databaseServiceBox;
    @FXML
    TextField mediumField;
    @FXML
    HBox mediumBox;
    @FXML
    TextField pagesCitedField1;
    @FXML
    TextField pagesCitedField2;
    @FXML
    HBox pagesCitedBox;
    @FXML
    TextField searchField;
    @FXML
    private ContextMenu rightClickMenu;
    @FXML
    private MenuItem deleteItem;

    List<Source> quickResults = new ArrayList<Source>();

    Service<Void> service = new Service<Void>() {//Just a default instantiation so the service is globally available
        @Override//it admittedly takes up a lot of space.
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    //Background work
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //FX Stuff done here
                            } finally {
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();
                    //Keep with the background work
                    return null;
                }
            };
        }
    };

    ContextMenu autocompleteMenu;

    private ObservableList<Source> testList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        titleBox.setOnMouseClicked((MouseEvent e) -> titleField.requestFocus());
        authorBox.setOnMouseClicked((MouseEvent e) -> authorField.requestFocus());
        magazineTitleBox.setOnMouseClicked((MouseEvent e) -> magazineTitleField.requestFocus());
        websiteTitleBox.setOnMouseClicked((MouseEvent e) -> websiteTitleField.requestFocus());
        volumeBox.setOnMouseClicked((MouseEvent e) -> volumeField.requestFocus());
        editionBox.setOnMouseClicked((MouseEvent e) -> editionField.requestFocus());
        issueBox.setOnMouseClicked((MouseEvent e) -> issueField.requestFocus());
        publisherBox.setOnMouseClicked((MouseEvent e) -> publisherField.requestFocus());
        yearPublishedBox.setOnMouseClicked((MouseEvent e) -> yearPublishedField.requestFocus());
        datePublishedBox.setOnMouseClicked((MouseEvent e) -> datePublishedField.requestFocus());
        URLBox.setOnMouseClicked((MouseEvent e) -> URLfield.requestFocus());
        versionBox.setOnMouseClicked((MouseEvent e) -> versionField.requestFocus());
        annotationBox.setOnMouseClicked((MouseEvent e) -> annotationField.requestFocus());
        databaseBox.setOnMouseClicked((MouseEvent e) -> databaseField.requestFocus());
        databaseServiceBox.setOnMouseClicked((MouseEvent e) -> databaseServiceField.requestFocus());
        mediumBox.setOnMouseClicked((MouseEvent e) -> mediumField.requestFocus());
        pagesCitedBox.setOnMouseClicked((MouseEvent e) -> pagesCitedField1.requestFocus());

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setText("Author");

        annotationColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
        annotationColumn.setText("Publisher");

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


        mediumColumn.setCellValueFactory(cellData -> cellData.getValue().mediumProperty());
        mediumColumn.setText("Medium");

        sourceTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSource(newValue)
        );

        rightClickMenu = new ContextMenu();
        deleteItem = new MenuItem("Delete Source");

        autocompleteMenu = new ContextMenu();
        MenuItem menu1 = new MenuItem("");
        menu1.setVisible(false);
        MenuItem menu2 = new MenuItem("");
        menu2.setVisible(false);
        MenuItem menu3 = new MenuItem("");
        menu3.setVisible(false);
        MenuItem menu4 = new MenuItem("Advanced Search...");
        autocompleteMenu.getItems().addAll(menu1, menu2, menu3, menu4);

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

        searchField.setOnKeyTyped((KeyEvent e) -> {
            if (service.isRunning()) {
                service.cancel();
            }
            if (!searchField.getText().equals("")) {
                service = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                //Background work
                                try {
                                    HttpResponse<JsonNode> jsonResponse = Unirest.get(mainWindow.searchURL + ":" + mainWindow.searchPort + "/library/_search")
                                            .header("accept", "application/json")
                                            .queryString("q", searchField.getText())
                                            .asJson();
                                    System.out.println("Search output:" + jsonResponse.getBody().toString());
                                    quickResults.clear();
                                    //convert json formatted return statement to Source objects, place in list
                                    String jsonString = jsonResponse.getBody().toString();
                                    quickResults.addAll(extractJSONSources(jsonString));
                                } catch (Exception ex) {
                                    System.out.println("Failed to communicate with server :c");
                                }
                                final CountDownLatch latch = new CountDownLatch(1);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            //FX Stuff done here
                                            //populate autocompleteMenu with proper results through communication with unirest
                                            if (quickResults.size() >= 3) {
                                                menu1.setText(quickResults.get(0).title.get());
                                                menu2.setText(quickResults.get(1).title.get());
                                                menu3.setText(quickResults.get(2).title.get());
                                                menu1.setVisible(true);
                                                menu2.setVisible(true);
                                                menu3.setVisible(true);
                                            } else if (quickResults.size() >= 2) {
                                                menu1.setText(quickResults.get(0).title.get());
                                                menu2.setText(quickResults.get(1).title.get());
                                                menu1.setVisible(true);
                                                menu2.setVisible(true);
                                                menu3.setVisible(false);
                                            } else if (quickResults.size() >= 1) {
                                                menu1.setText(quickResults.get(0).title.get());
                                                menu1.setVisible(true);
                                                menu2.setVisible(false);
                                                menu3.setVisible(false);
                                            } else {
                                                menu1.setVisible(false);
                                                menu2.setVisible(false);
                                                menu3.setVisible(false);
                                            }
                                            autocompleteMenu.show(searchField, Side.TOP, 0, 0);
                                        } finally {
                                            latch.countDown();
                                        }
                                    }
                                });
                                latch.await();
                                //Keep with the background work
                                return null;
                            }
                        };
                    }
                };
                service.start();
            } else {
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

    private ObservableList<Source> extractJSONSources(String jsonData) throws IOException {
        ObservableList<Source> result = FXCollections.observableArrayList();

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(jsonData);

        JsonToken jsonToken;

        Source bufferSource = new Source("");

        while (!parser.isClosed()) {
            jsonToken = parser.nextToken();
            String bufferString;

            //"Source" field captured.
            if (JsonToken.FIELD_NAME.equals(jsonToken) && parser.getText().equals("_source")) {

                //Scan through Source JSON object until "}" token is found.
                while (!JsonToken.END_OBJECT.equals(jsonToken)) {
                    jsonToken = parser.nextToken();

                    //Capture Source JSON fields in bufferSource.
                    if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                        switch (parser.getText()) {
                            case "Author":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setAuthor(bufferString);
                                break;
                            case "Title":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setTitle(bufferString);
                                break;
                            case "Volume":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setVolume(bufferString);
                                break;
                            case "Edition":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setEdition(bufferString);
                                break;
                            case "Publisher":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setPublisher(bufferString);
                                break;
                            case "Date":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setDatePublished(bufferString);
                                break;
                            case "WebsiteTitle":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setWebsiteTitle(bufferString);
                                break;
                            case "URL":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setURL(bufferString);
                                break;
                            case "Version":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setVersion(bufferString);
                                break;
                            case "Database:":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setDatabase(bufferString);
                                break;
                            case "DatabaseService":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setDatabaseService(bufferString);
                                break;
                            case "Medium":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setMedium(bufferString);
                                break;
                            case "PagesCitedStart":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setPagesCitedStart(bufferString);
                                break;
                            case "PagesCitedEnd":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setPagesCitedEnd(bufferString);
                                break;
                            case "MagTitle":
                                jsonToken = parser.nextToken();
                                bufferString = trimString(parser.getText());
                                bufferSource.setMagazineTitle(bufferString);
                                break;
                            default:
                                break;
                        }
                    }
                }
                //JSON Source captured in bufferSource.
                //Append bufferSource to result.
                result.add(bufferSource);
                //Reset bufferSource to capture more fields.
                bufferSource = new Source("");
            }
        }
        return result;
    }

    private String trimString(String input)
    {
        String result = input;

        if(result.isEmpty() || result.equals("null") || input.matches("null"))
        {
            return ("");
        }

        if(result.charAt(input.length() - 1) == '.'
                || result.charAt(input.length() - 1) == ',')
            result = result.substring(0, input.length() - 1);
        return result;
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


    public void UpdateSearchResults(ActionEvent actionEvent) {
        if (searchField.getText().equals("")) {
            //clear associated results list
        } else {
            //send string to elasticsearch
            //use results to populate associated results list
        }
    }
}
