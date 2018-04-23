import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;




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
    private TableColumn<Source, String> databaseColumn;
    @FXML
    private TableColumn<Source, String> databaseServiceColumn;

    @FXML
    private TableColumn<Source, String> datePublishedColumn;
    @FXML
    private TableColumn<Source, String> urlColumn;
    @FXML
    private TableColumn<Source, String> versionColumn;
    @FXML
    private TableColumn<Source, String> publisherColumn;
    @FXML
    private TableColumn<Source, String> mediumColumn;
    @FXML
    TextField titleField;
    @FXML
    TextField authorField;

    @FXML
    TextField magazineTitleField;

//    @FXML
//    TextField issueField;
//
//    @FXML
//    TextField mediumField;

    @FXML
    TextField volumeField;
    @FXML
    TextField editionField;

    @FXML
    TextField publisherField;




    @FXML
    TextField datePublishedField;

    @FXML
    TextField websiteTitleField;

    @FXML
    TextField urlField;

    @FXML
    TextField versionField;
//
//    @FXML
//    TextField databaseServiceField;





    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleColumn.setText("Title");

        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        authorColumn.setText("Author");

        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
        publisherColumn.setText("Publisher");

        websiteTitleColumn.setCellValueFactory(cellData -> cellData.getValue().websiteTitleProperty());
        websiteTitleColumn.setText("Website Title");

        urlColumn.setCellValueFactory(cellData -> cellData.getValue().URLProperty());
        urlColumn.setText("URL");

        magazineTitleColumn.setCellValueFactory(cellData -> cellData.getValue().magazineTitleProperty());
        magazineTitleColumn.setText("Magazine Title");

        issueColumn.setCellValueFactory(cellData -> cellData.getValue().issueProperty());
        issueColumn.setText("Issued");

        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
        volumeColumn.setText("Volume");

        editionColumn.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
        editionColumn.setText("Edition");

        versionColumn.setCellValueFactory(cellData -> cellData.getValue().versionProperty());
        versionColumn.setText("Version");

        databaseColumn.setCellValueFactory(cellData -> cellData.getValue().databaseProperty());
        databaseColumn.setText("Database");

        databaseServiceColumn.setCellValueFactory(cellData -> cellData.getValue().databaseServiceProperty());
        databaseServiceColumn.setText("Database Service");

//        yearPublishedColumn.setCellValueFactory(cellData -> cellData.getValue().yearPublishedProperty());
//        yearPublishedColumn.setText("Year Published");

        mediumColumn.setCellValueFactory(cellData -> cellData.getValue().mediumProperty());
        mediumColumn.setText("Medium");

        datePublishedColumn.setCellValueFactory(cellData -> cellData.getValue().datePublishedProperty());
        datePublishedColumn.setText("Date Published");
    }

    @FXML
    public void clearSearch(ActionEvent actionEvent) {
        try {
            searchTable.getItems().removeAll(testList);
        } catch (Exception e) {
            System.out.println(e);
        }
        titleField.setText("");
        authorField.setText("");
        magazineTitleField.setText("");
        volumeField.setText("");

        editionField.setText("");
        publisherField.setText("");
        versionField.setText("");
        datePublishedField.setText("");
        websiteTitleField.setText("");
        urlField.setText("");
        versionField.setText("");


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
        searchTable.setItems(testList);

        HttpResponse<JsonNode> jsonResponse = null;
        StringBuilder sb = new StringBuilder("");
        sb.append(titleField.getText()) .append(" ")
                .append(authorField.getText()) .append(" ")
                .append(magazineTitleField.getText()) .append(" ")
                .append(volumeField.getText()) .append(" ")
                .append(editionField.getText()) .append(" ")
                .append(publisherField.getText()) .append(" ")
                .append(versionField.getText()) .append(" ")
                .append(datePublishedField.getText()) .append(" ")
                .append(websiteTitleField.getText()) .append(" ")
                .append(versionField.getText()) .append(" ")
                .append(urlField.getText()) .append("")


        ;

        try {
            jsonResponse = Unirest.post(mainWindow.searchURL + ":" + mainWindow.searchPort + "/library/_search")
                    .header("accept", "application/json")
                    .header("content-type", "application/json; charset=UTF-8")
                    .queryString("q", sb.toString() )

                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println("Search output:" + jsonResponse.getBody().toString());
        testList.clear();
        //convert json formatted return statement to Source objects, place in list
        String jsonString = jsonResponse.getBody().toString();
        try {
            testList.addAll(extractJSONSources(jsonString));
        } catch (IOException e) {
            e.printStackTrace();
        }



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

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void ExitSearch(ActionEvent actionEvent){
        titleField.getScene().getWindow().hide();

    }

}
