import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private Main mainWindow;

    private TableColumn< Source, Boolean > selectedColumn = new TableColumn<>( "Select" );

    public Button exportButton;
    public Button cancelButton;
    private ObservableList<Source> sourceList;

    public void initSources()
    {
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
    }

    public void exportSources(ActionEvent actionEvent) {
        ObservableList<Source> exportList;
        exportList = FXCollections.observableArrayList();

        for(int i = 0; i < sourceTable.getItems().size(); i++)
        {
            if(selectedColumn.getCellObservableValue(i).getValue())
            {
                exportList.add(sourceTable.getItems().get(i));
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

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void exit(ActionEvent actionEvent) {
        sourceTable.getScene().getWindow().hide();
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
}