import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

public class MainWindowController {

    public static List<Source> sourceList;

    public void createNewSource(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SourceView.fxml"));
            primaryStage.setTitle("Source Viewer");
            primaryStage.setScene(new Scene(root));
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
}
