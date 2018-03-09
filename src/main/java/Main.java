import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    Stage primaryStage;
    GridPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         try{
         Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
         primaryStage.setTitle("Bibliography Management Tool");
         primaryStage.setScene(new Scene(root));
         MainWindowController.sourceList = new ArrayList<>();
         MainWindowController.currentlySelected = null;

         primaryStage.show();
         }
         catch(Exception e){
         System.out.print(e);
         }
         **/

        this.primaryStage = primaryStage;

        initRootLayout();
        initListOfSourcesView();
        initSourceView();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainWindow.fxml"));
            rootLayout = loader.load();

            Scene mainScene = new Scene(rootLayout,800,600);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Bibliography Management Tool");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initListOfSourcesView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ListOfSources.fxml"));
            AnchorPane listOfSources = loader.load();

            rootLayout.add(listOfSources, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initSourceView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SourceView.fxml"));
            AnchorPane sourceView = loader.load();

            rootLayout.add(sourceView, 1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}