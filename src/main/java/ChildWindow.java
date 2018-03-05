import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Callback;

public class ChildWindow {

    static Source returnSource;

    public static void Alert(String title, String message) {//opens alert windows, used for errors, messages, etc.
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    public static Source NewSource() {//opens a window to add a new source
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Source");
        window.setMinWidth(500);
        window.setOnCloseRequest(e -> window.close());

        Label intro = new Label("Enter source information:");

        TextField titleField = new TextField();
        titleField.setPromptText("Source title...");
        titleField.deselect();
        TextField authorField = new TextField();
        authorField.setPromptText("Author...");

        Button closeButton = new Button("Quit");
        Button saveButton = new Button("Save");
        closeButton.setOnAction(e -> window.close());
        saveButton.setOnAction(e -> {
            returnSource = new Source(titleField.getText(), authorField.getText());
            window.close();
        });

        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(5, 5, 5, 5));
        bottomBar.setSpacing(10);
        bottomBar.getChildren().addAll(saveButton, closeButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.getChildren().addAll(intro, titleField, authorField, bottomBar);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        closeButton.requestFocus();
        window.showAndWait();
        return returnSource;
    }

    public static Source EditSource(Source originalSource) {//Opens a window to edit existing sources
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Source");
        window.setMinWidth(500);
        window.setOnCloseRequest(e -> window.close());

        Label intro = new Label("Enter source information:");

        TextField titleField = new TextField(originalSource.title);
        titleField.setPromptText("Source title...");
        TextField authorField = new TextField(originalSource.author);
        authorField.setPromptText("Author...");

        Button closeButton = new Button("Quit");
        Button saveButton = new Button("Save");
        closeButton.setOnAction(e -> window.close());
        saveButton.setOnAction(e -> {
            returnSource = new Source(titleField.getText(), authorField.getText());
            window.close();
        });

        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(5, 5, 5, 5));
        bottomBar.setSpacing(10);
        bottomBar.getChildren().addAll(saveButton, closeButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.getChildren().addAll(intro, titleField, authorField, bottomBar);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        closeButton.requestFocus();
        window.showAndWait();
        return returnSource;
    }

}
