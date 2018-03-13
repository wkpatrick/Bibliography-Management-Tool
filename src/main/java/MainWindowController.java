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

    public static Source currentlySelected;

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

    public void UpdateSelected(ActionEvent actionEvent) {
        TableView<Source> source = (TableView) actionEvent.getSource();
        currentlySelected = source.getSelectionModel().getSelectedItem();
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
    private void openList(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(jsonFilter);
        fileChooser.setTitle("Select file to open");
        userFile = fileChooser.showOpenDialog(null);
        Source sTemp = new Source("");
        try (Scanner scan = new Scanner(userFile)) {
            while (scan.hasNextLine()) {
                //System.out.println(scan.nextLine());
                String x = "";
                if(scan.nextLine() != null) {
                     x = scan.nextLine();
                }

                if(x.contains("Author"))
                {

                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setAuthor(y);
                }
                if(x.contains("Title"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    //System.out.println(y);
                    sTemp.setTitle(y);
                }
                if(x.contains("Volume"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setVolume(y);
                }
                if(x.contains("Edition"))
                {
                   String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setEdition(y);
                }
                if(x.contains("Publisher"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setPublisher(y);
                }
                if(x.contains("DatePublished"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setDatePublished(y);
                }
                if(x.contains("WebsiteTitle"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setWebsiteTitle(y);
                }
                if(x.contains("URL"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setURL(y);
                }
                if(x.contains("Version"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setVersion(y);
                }
                if(x.contains("Annotation"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setAnnotation(y);
                }
                if(x.contains("Database") && !x.contains("Service"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setDatabase(y);
                }
                if(x.contains("DatabaseService"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setDatabaseService(y);
                }
                if(x.contains("Medium"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setMedium(y);
                }
                if(x.contains("PageCitedStart"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setPagesCitedStart(y);
                }
                if(x.contains("PagesCitedEnd"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setPagesCitedEnd(y);
                }
                if(x.contains("MagazineTitle") && x.contains("EOL"))
                {
                    String y = x.substring(x.indexOf(":")+1, x.indexOf(","));
                    sTemp.setMagazineTitle(y);
                    
                    //Here it should be adding the source to the source list, resetting the source temp, sTemp and starting again until we reach the end of the file.
                    mainWindow.addSource(sTemp);
                    sTemp = new Source("");
                }



            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void saveList(ActionEvent actionEvent){

        FileChooser saveFile = new FileChooser();
        saveFile.getExtensionFilters().add(jsonFilter);
        saveFile.setTitle("Save file");

        File file = saveFile.showSaveDialog(null);
        if(file != null) {
            try {

                String JsonFileToDisk = ""+
                        "           \t           \t“Author” : %s,\n" +
                        "           \t           \t“Title” : %s,\n" +
                        "           \t           \t“Volume” : %s,\n" +
                        "           \t           \t“Edition” : %s,\n" +
                        "           \t           \t“Publisher” : %s,\n" +
                        "                          \t“DatePublished” : %s,\n" +
                        "                          \t“WebsiteTitle” : %s,\n" +
                        "                          \t“URL” : %s,\n" +
                        "                          \t“Version” : %s,\n" +
                        "                          \t“Annotation” : %s,\n" +
                        "                          \t“Database” : %s,\n" +
                        "                          \t“DatabaseService” : %s,\n" +
                        "                          \t“Medium” : %s,\n" +
                        "                          \t“PagesCitedStart” : %s,\n" +
                        "                          \t“PagesCitedEnd” : %s,\n" +
                        "                          \t“MagazineTitle” : %s,EOL\n";

                FileWriter fileWriter = new FileWriter(file);
                //fileWriter.write(mainWindow.getSourceList().toString());
                for (Source str:mainWindow.getSourceList()) {
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
