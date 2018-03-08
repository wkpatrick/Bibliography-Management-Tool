import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.List;

public class MainWindowController {

    @FXML public static TableView<Source> sourceTable;

    public static List<Source> sourceList;
    public static Source currentlySelected;

    public void createNewSource(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SourceView.fxml"));
            primaryStage.setTitle("New Source");
            primaryStage.setScene(new Scene(root));
            //SourceView.Start();
            primaryStage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void Close(ActionEvent actionEvent) {
        //save current list?
        Platform.exit();
    }
    public void UpdateSelected(ActionEvent actionEvent)
    {
        TableView<Source> source = (TableView)actionEvent.getSource();
        currentlySelected = source.getSelectionModel().getSelectedItem();
    }
}
