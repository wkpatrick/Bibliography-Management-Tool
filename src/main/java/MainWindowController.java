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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private void openList(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(jsonFilter);
        fileChooser.setTitle("Select file to open");

        userFile = fileChooser.showOpenDialog(null);
        Source sTemp = new Source("");
        //ObservableList<Source> tempList = FXCollections.observableArrayList();;
        if (userFile.exists()) {
            mainWindow.sourceList.clear();
            try (Scanner scan = new Scanner(userFile)) {
                while (scan.hasNextLine()) {

                    String x = "";
                    if (scan.nextLine() != "") {
                        x = scan.nextLine();
                        //System.out.println(x);
                    }


                    if (x.contains("“Title”")) {
                        // System.out.println("Debug: "+ x);
                        // System.out.println("Debug2: " + x.substring(x.indexOf(":")+1, x.indexOf(",")) );
                        sTemp.setTitle(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                        // System.out.println(sTemp.getTitle());
                    }
                    if (x.contains("Author")) {

                        //String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                        sTemp.setAuthor(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Volume")) {
                        sTemp.setVolume(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Edition")) {
                        sTemp.setEdition(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Publisher")) {
                        sTemp.setPublisher(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("DatePublished")) {
                        sTemp.setDatePublished(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("WebsiteTitle")) {
                        sTemp.setWebsiteTitle(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("URL")) {
                        sTemp.setURL(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Version")) {
                        sTemp.setVersion(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Annotation")) {
                        sTemp.setAnnotation(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Database") && !x.contains("Service")) {
                        sTemp.setDatabase(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("DatabaseService")) {
                        sTemp.setDatabaseService(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("Medium")) {
                        sTemp.setMedium(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("PageCitedStart")) {
                        sTemp.setPagesCitedStart(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("PagesCitedEnd")) {
                        sTemp.setPagesCitedEnd(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }
                    if (x.contains("“MagazineTitle”")) {
                        sTemp.setMagazineTitle(x.substring(x.indexOf(":") + 1, x.indexOf(",")));
                    }

                    //Here it should be adding the source to the source list, resetting the source temp, sTemp and starting again until we reach the end of the file.
                    // System.out.println(sTemp.getTitle() + " hello hello");

                    mainWindow.addSource(sTemp);
                    sTemp = new Source("");
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }//end IF file exists.
    }

    @FXML
    private void saveList(ActionEvent actionEvent) {

        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(jsonFilter);
        saveFile.setTitle("Save file");

        File file = saveFile.showSaveDialog(null);

        if (file != null) {
            try {

                String JsonFileToDisk = "" +
                        "“Author”:%s,\n" +
                        "“Title”:%s,\n" +
                        "“Volume”:%s,\n" +
                        "“Edition”:%s,\n" +
                        "“Publisher”:%s,\n" +
                        "“DatePublished”:%s,\n" +
                        "“WebsiteTitle”:%s,\n" +
                        "“URL”:%s,\n" +
                        "“Version”:%s,\n" +
                        "“Annotation”:%s,\n" +
                        "“Database”:%s,\n" +
                        "“DatabaseService”:%s,\n" +
                        "“Medium”:%s,\n" +
                        "“PagesCitedStart”:%s,\n" +
                        "“PagesCitedEnd”:%s,\n" +
                        "“MagazineTitle”:%s,\n";

                FileWriter fileWriter = new FileWriter(file);
                //fileWriter.write(mainWindow.getSourceList().toString());
                for (Source str : mainWindow.getSourceList()) {
                    System.out.println(str.getTitle());
                    String temp = String.format(JsonFileToDisk, str.getAuthor(), str.getTitle(),
                            str.getVersion(), str.getEdition(), str.getPublisher(), str.getDatePublished(),
                            str.getWebsiteTitle(), str.getURL(), str.getVersion(), str.getAnnotation(),
                            str.getDatabase(), str.getDatabaseService(), str.getMedium(), str.getPagesCitedStart(),
                            str.getPagesCitedEnd(), str.getMagazineTitle());

                    fileWriter.write(temp);
                }
                fileWriter.close();


            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


}
