import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

public class ExportController {
    public TableView<Source> sourceTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
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
        ObservableList<Source> exportList = sourceList;
        exportList.clear();

        for(int i = 0; i < sourceTable.getItems().size(); i++)
        {
            if(selectedColumn.getCellObservableValue(i).getValue())
            {
                exportList.add(sourceTable.getItems().get(i));
            }
        }
    }

    public void exit(ActionEvent actionEvent) {
        sourceTable.getScene().getWindow().hide();
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
}