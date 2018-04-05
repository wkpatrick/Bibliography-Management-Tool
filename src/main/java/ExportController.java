import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

//TODO: Set custom source Export styles: drop-down list in ExportController
//TODO: Create export settings file
//TODO: Settings > Citation Settings > Open new UI
//TODO: Edit custom source Export styles: new UI
//TODO: Save custom source Export styles: new UI
//TODO: Open custom source Export styles, for editing/saving: new UI
//JUST for exporting- not across the whole application.

public class ExportController {
    public TableView<Source> sourceTable;
    public JFXToggleButton toggleButtonStyle;
    public ChoiceBox<String> styleChoiceBox;
    public CheckBox styleCheckbox;
    public HBox toggleContainer;
    private Main mainWindow;

    private TableColumn< Source, Boolean > selectedColumn = new TableColumn<>( "Select" );

    public Button exportButton;
    public Button cancelButton;
    private ObservableList<Source> sourceList;

    private boolean customStyle;

    void initSources()
    {
        customStyle = false;
        ObservableList<String> styleNames = FXCollections.observableArrayList();
        sourceTable.setEditable(true);
        selectedColumn.setCellValueFactory( f -> f.getValue().isSelected());
        selectedColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        sourceTable.getColumns().add(selectedColumn);

        ObservableList<Source> data = sourceTable.getItems();

        try
        {
            sourceList = mainWindow.getSourceList();
            data.addAll(sourceList);
        }
        catch(Exception e)
        {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        }

        File directory = new File("styles/");
        ObservableList<File> fileList = FXCollections.observableArrayList();
        fileList.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles())));

        for(File file:fileList)
        {
            styleNames.addAll(file.getName());
        }

        styleChoiceBox.setItems(styleNames);
    }

    public void exportSources(ActionEvent actionEvent) {
        ObservableList<Source> exportList;
        exportList = FXCollections.observableArrayList();

        for(int i = 0; i < sourceTable.getItems().size(); i++)
        {
            if(selectedColumn.getCellObservableValue(i).getValue())
            {
                exportList.add(sourceTable.getItems().get(i));
                sourceList.get(i).setSelected(true);
            }
            else
            {
                sourceList.get(i).setSelected(false);
            }
        }

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ExportDisplay.fxml"));

            GridPane rootLayout = (GridPane) loader.load();

            primaryStage.setTitle("Exported Sources");
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();

            ExportDisplayController controller = loader.getController();
            controller.loadSources(exportList, toggleButtonStyle.isSelected());
            if(customStyle)
                controller.initCustom(styleChoiceBox.getValue());

        } catch (Exception e) {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        }
    }

    public void exit(ActionEvent actionEvent) {
        sourceTable.getScene().getWindow().hide();
    }

    void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setCustomStyle(ActionEvent actionEvent) {
        customStyle = styleCheckbox.isSelected();

        if(customStyle)
        {
            toggleContainer.setDisable(true);
            toggleContainer.setOpacity(0.5);
        }
        else
        {
            toggleContainer.setDisable(false);
            toggleContainer.setOpacity(1);
        }
    }
}