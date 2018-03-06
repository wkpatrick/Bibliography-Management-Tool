import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Establish primary stage
        primaryStage.setTitle("Bibliography Management Tool");

        //Setup basic BorderPane
        BorderPane layout = new BorderPane();

        //Setup right HBox
        HBox rightDisplay = new HBox();
        rightDisplay.setAlignment(Pos.TOP_LEFT);
        rightDisplay.setPadding(new Insets(5, 5, 5, 7));

        //Setup left VBox
        VBox bottomDisplay = new VBox();
        bottomDisplay.setAlignment(Pos.TOP_LEFT);

        //Setup bottom row HBox
        HBox buttonBar = new HBox();
        buttonBar.setPadding(new Insets(8, 5, 5, 3));
        buttonBar.setSpacing(10);

        //Setup buttons in buttonbar
        JFXButton newSource = new JFXButton("New");
        JFXButton editSource = new JFXButton("Edit");
        JFXButton deleteSource = new JFXButton("Delete");
        buttonBar.getChildren().addAll(newSource, editSource, deleteSource);

        //Setup text display
        Label sourceDisplay = new Label("");

        //Setup TableView Columns
        //Title Column
        TableColumn<Source, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //Author Column
        TableColumn<Source, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        //Setup TableView Object
        TableView<Source> sourceTable = new TableView<>();

        sourceTable.setMinHeight(500);
        sourceTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        sourceTable.setMinSize(400, 10);
        sourceTable.getColumns().addAll(titleColumn, authorColumn);
        sourceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldProperty, newProperty) -> {
            if (newProperty != null) {
                sourceDisplay.setText(newProperty.title + '\n' + newProperty.author.toString());
            } else {
                sourceDisplay.setText("");
            }
        });

        //Setup MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu settingsMenu = new Menu("Settings");
        menuBar.getMenus().addAll(fileMenu, settingsMenu);

        //Setup File submenu
        MenuItem newButton = new MenuItem("_New List...");
        MenuItem openButton = new MenuItem("_Open List...");
        MenuItem closeButton = new MenuItem("_Exit");
        fileMenu.getItems().addAll(newButton, openButton, closeButton);

        //Configure all button functionality
        closeButton.setOnAction(e -> CloseProgram(primaryStage));
        newSource.setOnAction(e -> sourceTable.getItems().add(ChildWindow.NewSource()));
        deleteSource.setOnAction(e -> sourceTable.getItems().remove(sourceTable.getSelectionModel().getSelectedItem()));
        editSource.setOnAction(e -> {
            if (sourceTable.getSelectionModel().getSelectedItem() != null) {
                Source editedSource = ChildWindow.EditSource(sourceTable.getSelectionModel().getSelectedItem());
                int i = sourceTable.getSelectionModel().getSelectedIndex();
                sourceTable.getItems().remove(sourceTable.getSelectionModel().getSelectedItem());
                sourceTable.getItems().add(i, editedSource);
            }
        });

        //Set program options
        primaryStage.setOnCloseRequest(e -> CloseProgram(primaryStage));

        //Add all elements to respective parents
        rightDisplay.getChildren().add(sourceDisplay);
        bottomDisplay.getChildren().addAll(sourceTable, buttonBar);

        //Add all elements to borderpane
        layout.setTop(menuBar);
        layout.setLeft(sourceTable);
        layout.setBottom(bottomDisplay);
        layout.setCenter(rightDisplay);

        //Define final scene measurements
        Scene scene = new Scene(layout, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void CloseProgram(Stage primaryStage) {
        //Potential future code to save open lists, etc.
        primaryStage.close();
    }

}
