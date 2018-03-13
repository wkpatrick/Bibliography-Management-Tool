import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

public class ExportController {
    public TableView<Source> sourceTable;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    private Main mainWindow;


    public Button exportButton;
    public Button cancelButton;
    private ObservableList<Source> sourceList;
    private ObservableList<Source> exportList;

    public void initSources()
    {
        sourceTable.setEditable(true);

        ObservableList<Source> data = sourceTable.getItems();

        try
        {
            sourceList = mainWindow.getSourceList();
            data.addAll(sourceList);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void exportSources(ActionEvent actionEvent) {

    }

    public void exit(ActionEvent actionEvent) {
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
}