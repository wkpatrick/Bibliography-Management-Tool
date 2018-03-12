import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExportController {
    public TableView sourceTable;
    public TableColumn selectColumn;
    public TableColumn titleColumn;
    public TableColumn authorColumn;
    private Main mainWindow;

    public Button exportButton;
    public Button cancelButton;
    private ObservableList<Source> sourceList;

    public void initSources()
    {
        ObservableList data = sourceTable.getItems();

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