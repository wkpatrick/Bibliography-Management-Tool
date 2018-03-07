import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            primaryStage.setTitle("Bibliography Management Tool");
            primaryStage.setScene(new Scene(root));
            MainWindowController.sourceList = new ArrayList<>();
            primaryStage.show();
        }
        catch(Exception e){
            System.out.print(e);
        }
    }

}