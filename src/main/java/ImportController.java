import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ImportController
{
    public TableView<Source> sourceTable;
    private Main mainWindow;

    public TextField filepathField;
    public JFXButton filepathButton;
    public JFXButton openButton;
    public JFXButton okButton;
    public JFXButton cancelButton;
    private File inputFile;

    private boolean validSources = false;

    private ObservableList<Source> importList;

    private TableColumn< Source, Boolean > selectedColumn = new TableColumn<>( "Select" );

    public void openFile(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sources File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Source Files", "*.source"));
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


    /********************************************************************************/
    /*                     EXTRACTS CONTENTS FROM A FILE                            */
    /********************************************************************************/
    public void openContents(ActionEvent actionEvent) throws IOException {
        importList = FXCollections.observableArrayList();

        sourceTable.getItems().clear();

        if(!validFile())
            return;

        byte[] rawData = Files.readAllBytes(Paths.get(inputFile.getAbsolutePath()));
        String jsonData = new String(rawData);

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(jsonData);
        JsonToken jsonToken;

        Source bufferSource = new Source("");

        while(!parser.isClosed()){
            jsonToken = parser.nextToken();

            if(JsonToken.START_OBJECT.equals(jsonToken))
            {
                bufferSource = new Source("");
            }

            if(JsonToken.END_OBJECT.equals(jsonToken))
            {
                importList.add(bufferSource);
            }

            if(JsonToken.FIELD_NAME.equals(jsonToken))
            {
                switch(parser.getText())
                {
                    case "Author":
                        jsonToken = parser.nextToken();
                        bufferSource.setAuthor(parser.getText());
                        break;
                    case "Title":
                        jsonToken = parser.nextToken();
                        bufferSource.setTitle(parser.getText());
                        break;
                    case "Volume":
                        jsonToken = parser.nextToken();
                        bufferSource.setVolume(parser.getText());
                        break;
                    case "Edition":
                        jsonToken = parser.nextToken();
                        bufferSource.setEdition(parser.getText());
                        break;
                    case "Publisher":
                        jsonToken = parser.nextToken();
                        bufferSource.setPublisher(parser.getText());
                        break;
                    case "DatePublished":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatePublished(parser.getText());
                        break;
                    case "WebsiteTitle":
                        jsonToken = parser.nextToken();
                        bufferSource.setWebsiteTitle(parser.getText());
                        break;
                    case "URL":
                        jsonToken = parser.nextToken();
                        bufferSource.setURL(parser.getText());
                        break;
                    case "Version":
                        jsonToken = parser.nextToken();
                        bufferSource.setVersion(parser.getText());
                        break;
                    case "Annotation":
                        jsonToken = parser.nextToken();
                        bufferSource.setAnnotation(parser.getText());
                        break;
                    case "Database:":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatabase(parser.getText());
                        break;
                    case "DatabaseService":
                        jsonToken = parser.nextToken();
                        bufferSource.setDatabaseService(parser.getText());
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
                    case "MagTitle":
                        jsonToken = parser.nextToken();
                        bufferSource.setMagazineTitle(parser.getText());
                        break;
                    default:
                        break;
                }
            }
        }

        //Start block:
        //This initializes checkboxes into a table- not relevant to Open list due to selecting ALL sources.
        sourceTable.setEditable(true);
        selectedColumn.setCellValueFactory( f -> f.getValue().isSelected());
        selectedColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        sourceTable.getColumns().add(selectedColumn);
        //End block

        //This initializes the TableView to display all of the sources.
        try
        {
            ObservableList<Source> data = sourceTable.getItems();
            data.addAll(importList);
            validSources = true;
        }
        catch(Exception e)
        {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        }

        /* What sourceTable looks like in the FXML layout.

        <TableView fx:id="sourceTable" GridPane.columnIndex="0" GridPane.rowIndex="2">
        <columnResizePolicy><TableView fx:constant="CONSTRAINED_RESIZE_POLICY" /></columnResizePolicy>
        <columns>
            <TableColumn text="Title">
                <cellValueFactory>                              <-----------
                    <PropertyValueFactory property="title" />   <----------- Required to map the table directly
                </cellValueFactory>                             <----------- to the "title" StringProperty field.
            </TableColumn>
            <TableColumn text="Author(s)">
                <cellValueFactory>                              <-----------
                    <PropertyValueFactory property="author" />  <----------- Required to map the table directly
                </cellValueFactory>                             <----------- to the "author" StringProperty field.
            </TableColumn>
        </columns>
        </TableView>

         */
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

    public void copyImports(ActionEvent actionEvent) {
        if(!validSources)
        {
            return;
        }

        for(int i = 0; i < sourceTable.getItems().size(); i++)
        {
            if(!selectedColumn.getCellObservableValue(i).getValue())
            {
                importList.remove(sourceTable.getItems().get(i));
            }
        }

        mainWindow.sourceList.addAll(importList);
        cancelButton.getScene().getWindow().hide();
    }
}