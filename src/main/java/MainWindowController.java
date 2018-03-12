import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindowController {

    private Main mainWindow;

    public static Source currentlySelected;

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

}
