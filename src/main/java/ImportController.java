import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ImportController
{
    public ListView<String> sourcesDisplayList;
    private Main mainWindow;

    public TextField filepathField;
    public Button filepathButton;
    public Button openButton;
    public Button okButton;
    public Button cancelButton;
    private File inputFile;

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
        catch(Exception e)
        {
            //noinspection ThrowablePrintedToSystemOut
            System.out.print(e);
        }
    }

    public void Close(){
        cancelButton.getScene().getWindow().hide();
    }

    //TODO: Open multiple json objects from a file.
    public void openContents(ActionEvent actionEvent) throws IOException {
        sourcesDisplayList.getItems().clear();

        if(!validFile())
            return;

        byte[] rawData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
        String fullData = new String(rawData);

        ArrayList<String> returnString = new ArrayList<>();
        ArrayList<String> outputString = new ArrayList<>();

        String buffer;

        int temp = 0;
        for(int index = 0; index < fullData.length(); index++)
        {
            if(fullData.charAt(index) == ',')
            {
                returnString.add(fullData.substring(temp, index));
                temp = index+1;
            }
        }

        sourcesDisplayList.getItems().addAll(returnString);
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