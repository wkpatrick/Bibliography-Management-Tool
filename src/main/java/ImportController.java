import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class ImportController
{
    private Main mainWindow;

    public TextField filepathField;
    public Button filepathButton;
    public Button openButton;
    public ListView sourcesList;
    public Button okButton;
    public Button cancelButton;
    private File inputFile;

    public List<Source> importSources;
    private Source testSource;

    public void openFile(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sources File");
        fileChooser.getExtensionFilters().add
                (
                        new FileChooser.ExtensionFilter("Source Files", "*.source")
                );
        try
        {
            inputFile = fileChooser.showOpenDialog(new Stage());
            filepathField.setText(inputFile.getAbsolutePath());
        }
        catch(Exception ignored)
        {

        }
    }

    public void Close(){
        cancelButton.getScene().getWindow().hide();
    }

    //Currently opens files with just ONE json object
    //TODO: Open multiple json objects from a file.
    public void openContents(ActionEvent actionEvent) throws IOException {
        sourcesList.getItems().clear();

        if(!validFile())
            return;

        byte[] jsonData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
        ObjectMapper objectMapper = new ObjectMapper();

        testSource = objectMapper.readValue(jsonData, Source.class);

        System.out.println(testSource.getTitle());
    }

    private boolean validFile()
    {
        if(inputFile == null && filepathField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No File Chosen");
            alert.setContentText("Please select a source file to import sources.");

            alert.showAndWait();

            return false;
        }
        else
        {
            inputFile = new File(filepathField.getText());
            if(!inputFile.exists())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("File Does Not Exist");
                alert.setContentText("Please select a source file to import sources.");

                alert.showAndWait();

                return false;
            }
        }

        return true;
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
}