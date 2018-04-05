//These imports are for making use of jason files.
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

//JavaFX imports, some need to be purged later.
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//Java file IO
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainWindowController {

    private Main mainWindow;

    //Some variables so I can access some of the fxml objects
    //private Main mainWindow;
    @FXML
    private MenuItem openList;
    @FXML
    private MenuItem saveList;
    File userFile;
    //Filter so only a .json is written out
    FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON", "*.json");

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




    public void Close(ActionEvent actionEvent) {
        //save current list?
        Platform.exit();
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void createNewList(ActionEvent actionEvent) {
        mainWindow.sourceList.clear();
    }


    public void importSources(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Import.fxml"));

            GridPane rootLayout = (GridPane) loader.load();

            primaryStage.setTitle("Import Sources");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            ImportController controller = loader.getController();
            controller.setMainWindow(this.mainWindow);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void exportSources(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Export.fxml"));

            GridPane rootLayout = (GridPane) loader.load();

            primaryStage.setTitle("Export Sources");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            ExportController controller = loader.getController();
            controller.setMainWindow(this.mainWindow);
            controller.initSources();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Open and save, Randolph
    @FXML
    private void openList(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(jsonFilter);
        fileChooser.setTitle("Select file to open");

        userFile = fileChooser.showOpenDialog(null);
        if(userFile == null)
            return;

        Source sTemp = new Source("");
        //ObservableList<Source> tempList = FXCollections.observableArrayList();
        byte[] rawData = Files.readAllBytes(Paths.get(userFile.getAbsolutePath()));
        String jsonData = new String(rawData);

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(jsonData);
        JsonToken jsonToken;

        Source bufferSource = new Source("");
        mainWindow.sourceList.clear();
        while(!parser.isClosed()){
            jsonToken = parser.nextToken();
            //start and stop of JSON
            if(JsonToken.START_OBJECT.equals(jsonToken)){

                bufferSource = new Source("");
            }
            if(JsonToken.END_OBJECT.equals(jsonToken)){
                mainWindow.addSource(bufferSource);
            }

            if(jsonToken.FIELD_NAME.equals(jsonToken)){
                System.out.println(parser.getText());
                switch (parser.getText())
                {
                    //each switch case sets a field in the bufferSource object, once its done the End object
                    //above should trigger and move to the next "object or source"

                    case "Title":
                        jsonToken = parser.nextToken();
                        bufferSource.setTitle(parser.getText());
                        break;

                    case "Author":
                        jsonToken = parser.nextToken();
                        bufferSource.setAuthor(parser.getText());
                        break;

                    case "MagTitle":
                        jsonToken = parser.nextToken();
                        bufferSource.setMagazineTitle(parser.getText());
                        break;

                    case "WebsiteTitle":
                        jsonToken = parser.nextToken();
                        bufferSource.setWebsiteTitle(parser.getText());
                        break;

                    case "Volume":
                        jsonToken = parser.nextToken();
                        bufferSource.setVolume(parser.getText());
                        break;

                    case "Edition":
                        jsonToken = parser.nextToken();
                        bufferSource.setEdition(parser.getText());
                        break;

                    case "Issue":
                        jsonToken = parser.nextToken();
                        bufferSource.setIssue(parser.getText());
                        break;

                    case "Publisher":
                        jsonToken = parser.nextToken();
                        bufferSource.setPublisher(parser.getText());
                        break;

                    case "YearPublished":
                        jsonToken = parser.nextToken();
                        bufferSource.setYearPublished(parser.getText());
                        break;

                    case "DatePublished":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatePublished(parser.getText());
                        break;

                    case "URL":
                        jsonToken = parser.nextToken();
                        bufferSource.setURL(parser.getText());
                        break;

                    case "Version":
                        jsonToken = parser.nextToken();
                        bufferSource.setVersion(parser.getText());
                        break;

                    case "Database":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatabase(parser.getText());
                        break;

                    case "DatabaseService":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatabaseService(parser.getText());
                        //confound this
                        System.out.println("Debug DatabaseService value read: "+parser.getText());
                        System.out.println("Debug DatabaseService value set as: "+bufferSource.getDatabaseService());
                        break;

                    case "Medium":
                        jsonToken = parser.nextToken();
                        bufferSource.setMedium(parser.getText());
                        break;

                    case "PagesCitedStart":
                        jsonToken = parser.nextToken();
                        bufferSource.setPagesCitedStart(parser.getText());
                        break;

                    case "PagesCitedEnd":
                        jsonToken = parser.nextToken();
                        bufferSource.setPagesCitedEnd(parser.getText());
                        break;

                    case "Annotation":
                        jsonToken = parser.nextToken();
                        bufferSource.setAnnotation(parser.getText());
                        break;

                    default:
                        break;
                }


            }//End massive if block that parses through json tokens.

        }//End while parser is not closed loop

    } //end open list block

    @FXML
    private void saveList(ActionEvent actionEvent) {

        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(jsonFilter);
        saveFile.setTitle("Save file");

        File file = saveFile.showSaveDialog(null);

        if (file != null) {
            try {

                String JsonFileToDisk = "\n{" +
                        "\"Title\":\"%s\",\n" +
                        "\"Author\":\"%s\",\n" +
                        "\"MagTitle\":\"%s\",\n" +
                        "\"WebsiteTitle\":\"%s\",\n" +
                        "\"Volume\":\"%s\",\n" +
                        "\"Edition\":\"%s\",\n" +
                        "\"Issue\":\"%s\",\n" +
                        "\"Publisher\":\"%s\",\n" +
                        "\"YearPublished\":\"%s\",\n" +
                        "\"DatePublished\":\"%s\",\n" +
                        "\"URL\":\"%s\",\n" +
                        "\"Version\":\"%s\",\n" +
                        "\"Database\":\"%s\",\n" +
                        "\"DatabaseService\":\"%s\",\n" +
                        "\"Medium\":\"%s\",\n" +
                        "\"PagesCitedStart\":\"%s\",\n" +
                        "\"PagesCitedEnd\":\"%s\",\n" +
                        "\"Annotation\":\"%s\"\n}";

                FileWriter fileWriter = new FileWriter(file);
                //fileWriter.write(mainWindow.getSourceList().toString());
                for (Source str : mainWindow.getSourceList()) {
                    //System.out.println(str.getTitle());
                    String temp = String.format(JsonFileToDisk, str.getTitle(), str.getAuthor(), str.getMagazineTitle(), str.getWebsiteTitle(),
                            str.getVolume(), str.getEdition(), str.getIssue(), str.getPublisher(), str.getYearPublished(), str.getDatePublished(),
                            str.getURL(), str.getVersion(), str.getDatabase(), str.getDatabaseService(), str.getMedium(), str.getPagesCitedStart(),
                            str.getPagesCitedEnd(), str.getAnnotation());

                    fileWriter.write(temp+".json");
                }
                fileWriter.close();


            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }//end save list block


    public void customCitation(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CiteStyle.fxml"));

            GridPane rootLayout = (GridPane) loader.load();

            primaryStage.setTitle("Custom Citation Styles");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            CiteStyleController controller = loader.getController();
            controller.setMainWindow(this.mainWindow);
            controller.init();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
