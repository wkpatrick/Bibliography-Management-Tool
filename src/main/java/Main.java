import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    Stage primaryStage;
    GridPane rootLayout;

    ObservableList<Source> sourceList;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sourceList = FXCollections.observableArrayList();
        this.primaryStage = primaryStage;

        initRootLayout();
        initSourceListView();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainWindow.fxml"));
            rootLayout = loader.load();

            MainWindowController controller = loader.getController();
            controller.setMainWindow(this);

            Scene mainScene = new Scene(rootLayout, 800, 600);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Bibliography Management Tool");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSourceListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SourceListView.fxml"));
            AnchorPane listOfSources = loader.load();

            rootLayout.add(listOfSources, 0, 1, 2, 1);
            SourceListViewController controller = loader.getController();
            controller.setMainWindow(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<Source> getSourceList() {

        return sourceList;
    }

    public void addSource(Source source) {
        sourceList.add(source);
    }

}