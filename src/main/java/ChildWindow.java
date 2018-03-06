import com.jfoenix.controls.JFXButton;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChildWindow {

    static Source returnSource;

    public static void Alert(String title, String message) {//opens alert windows, used for errors, messages, etc.
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);

        Label label = new Label();
        label.setText(message);
        JFXButton closeJFXButton = new JFXButton("Close");
        closeJFXButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeJFXButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    public static Source NewSource() {//opens a window to add a new source
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Source");
        window.setMinWidth(450);
        window.setMinHeight(720);
        window.setOnCloseRequest(e -> window.close());

        Label intro = new Label("Enter source information:");

        HBox titleLine = new HBox();
        titleLine.setPadding(new Insets(0, 5, 0, 5));
        titleLine.setSpacing(10);
        TextField titleField = new TextField();
        titleField.setPromptText("");
        Label titleLabel = new Label("Title:");
        titleLabel.setMinSize(100,0);
        titleLine.getChildren().addAll(titleLabel, titleField);

        HBox authorLine = new HBox();
        authorLine.setPadding(new Insets(0, 5, 0, 5));
        authorLine.setSpacing(10);
        TextField authorField = new TextField();
        authorField.setPromptText("");
        Label authorLabel = new Label("Author:");
        authorLabel.setMinSize(100,0);
        authorLine.getChildren().addAll(authorLabel, authorField);

        HBox magazineTitleLine = new HBox();
        magazineTitleLine.setPadding(new Insets(0, 5, 0, 5));
        magazineTitleLine.setSpacing(10);
        TextField magazineTitleField = new TextField();
        magazineTitleField.setPromptText("");
        Label magazineTitleLabel = new Label("Magazine Title:");
        magazineTitleLabel.setMinSize(100,0);
        magazineTitleLine.getChildren().addAll(magazineTitleLabel, magazineTitleField);

        HBox websiteTitleLine = new HBox();
        websiteTitleLine.setPadding(new Insets(0, 5, 0, 5));
        websiteTitleLine.setSpacing(10);
        TextField websiteTitleField = new TextField();
        websiteTitleField.setPromptText("");
        Label websiteTitleLabel = new Label("Website Title:");
        websiteTitleLabel.setMinSize(100,0);
        websiteTitleLine.getChildren().addAll(websiteTitleLabel, websiteTitleField);

        HBox volumeLine = new HBox();
        volumeLine.setPadding(new Insets(0, 5, 0, 5));
        volumeLine.setSpacing(10);
        TextField volumeField = new TextField();
        volumeField.setPromptText("");
        Label volumeLabel = new Label("Volume:");
        volumeLabel.setMinSize(100,0);
        volumeLine.getChildren().addAll(volumeLabel, volumeField);

        HBox editionLine = new HBox();
        editionLine.setPadding(new Insets(0, 5, 0, 5));
        editionLine.setSpacing(10);
        TextField editionField = new TextField();
        editionField.setPromptText("");
        Label editionLabel = new Label("Edition:");
        editionLabel.setMinSize(100,0);
        editionLine.getChildren().addAll(editionLabel, editionField);

        HBox issueLine = new HBox();
        issueLine.setPadding(new Insets(0, 5, 0, 5));
        issueLine.setSpacing(10);
        TextField issueField = new TextField();
        issueField.setPromptText("");
        Label issueLabel = new Label("Issue:");
        issueLabel.setMinSize(100,0);
        issueLine.getChildren().addAll(issueLabel, issueField);

        HBox publisherLine = new HBox();
        publisherLine.setPadding(new Insets(0, 5, 0, 5));
        publisherLine.setSpacing(10);
        TextField publisherField = new TextField();
        publisherField.setPromptText("");
        Label publisherLabel = new Label("Publisher:");
        publisherLabel.setMinSize(100,0);
        publisherLine.getChildren().addAll(publisherLabel, publisherField);

        HBox yearPublishedLine = new HBox();
        yearPublishedLine.setPadding(new Insets(0, 5, 0, 5));
        yearPublishedLine.setSpacing(10);
        TextField yearPublishedField = new TextField();
        yearPublishedField.setPromptText("");
        Label yearPublishedLabel = new Label("Year Published:");
        yearPublishedLabel.setMinSize(100,0);
        yearPublishedLine.getChildren().addAll(yearPublishedLabel, yearPublishedField);

        HBox datePublishedLine = new HBox();
        datePublishedLine.setPadding(new Insets(0, 5, 0, 5));
        datePublishedLine.setSpacing(10);
        TextField datePublishedField = new TextField();
        datePublishedField.setPromptText("");
        Label datePublishedLabel = new Label("Date Published:");
        datePublishedLabel.setMinSize(100,0);
        datePublishedLine.getChildren().addAll(datePublishedLabel, datePublishedField);

        HBox URLLine = new HBox();
        URLLine.setPadding(new Insets(0, 5, 0, 5));
        URLLine.setSpacing(10);
        TextField URLField = new TextField();
        URLField.setPromptText("");
        Label URLLabel = new Label("URL:");
        URLLabel.setMinSize(100,0);
        URLLine.getChildren().addAll(URLLabel, URLField);

        HBox versionLine = new HBox();
        versionLine.setPadding(new Insets(0, 5, 0, 5));
        versionLine.setSpacing(10);
        TextField versionField = new TextField();
        versionField.setPromptText("");
        Label versionLabel = new Label("Version:");
        versionLabel.setMinSize(100,0);
        versionLine.getChildren().addAll(versionLabel, versionField);

        HBox annotationLine = new HBox();
        annotationLine.setPadding(new Insets(0, 5, 0, 5));
        annotationLine.setSpacing(10);
        TextField annotationField = new TextField();
        annotationField.setPromptText("");
        Label annotationLabel = new Label("Annotation:");
        annotationLabel.setMinSize(100,0);
        annotationLine.getChildren().addAll(annotationLabel, annotationField);

        HBox databaseLine = new HBox();
        databaseLine.setPadding(new Insets(0, 5, 0, 5));
        databaseLine.setSpacing(10);
        TextField databaseField = new TextField();
        databaseField.setPromptText("");
        Label databaseLabel = new Label("Database:");
        databaseLabel.setMinSize(100,0);
        databaseLine.getChildren().addAll(databaseLabel, databaseField);

        HBox databaseServiceLine = new HBox();
        databaseServiceLine.setPadding(new Insets(0, 5, 0, 5));
        databaseServiceLine.setSpacing(10);
        TextField databaseServiceField = new TextField();
        databaseServiceField.setPromptText("");
        Label databaseServiceLabel = new Label("Database Service:");
        databaseServiceLabel.setMinSize(100,0);
        databaseServiceLine.getChildren().addAll(databaseServiceLabel, databaseServiceField);

        HBox mediumLine = new HBox();
        mediumLine.setPadding(new Insets(0, 5, 0, 5));
        mediumLine.setSpacing(10);
        TextField mediumField = new TextField();
        mediumField.setPromptText("");
        Label mediumLabel = new Label("Medium:");
        mediumLabel.setMinSize(100,0);
        mediumLine.getChildren().addAll(mediumLabel, mediumField);

        HBox pagesCitedLine = new HBox();
        pagesCitedLine.setPadding(new Insets(0, 5, 0, 5));
        pagesCitedLine.setSpacing(10);
        TextField pagesCited1Field = new TextField();
        pagesCited1Field.setPromptText("");
        TextField pagesCited2Field = new TextField();
        pagesCited2Field.setPromptText("");
        Text dash = new Text("-");
        Label pagesCitedLabel = new Label("Pages Cited:");
        pagesCitedLabel.setMinSize(100,0);
        pagesCitedLine.getChildren().addAll(pagesCitedLabel, pagesCited1Field, dash, pagesCited2Field);

        VBox inputVBox = new VBox();
        inputVBox.setPadding(new Insets(5, 5, 5, 5));
        inputVBox.setSpacing(10);
        inputVBox.getChildren().addAll(titleLine, authorLine, magazineTitleLine, websiteTitleLine, volumeLine,
                editionLine, issueLine, publisherLine, yearPublishedLine, datePublishedLine, URLLine, versionLine,
                databaseLine, databaseServiceLine, mediumLine, pagesCitedLine, annotationLine);

        JFXButton closeJFXButton = new JFXButton("Quit");
        JFXButton saveJFXButton = new JFXButton("Save");
        closeJFXButton.setOnAction(e -> window.close());
        saveJFXButton.setOnAction(e -> {
            ArrayList<String> authorList = new ArrayList<>(Arrays.asList(authorField.getText().split("\\s*,\\s*")));
            returnSource = new Source(titleField.getText());
            returnSource.author = authorList;
            returnSource.setMagazineTitle(magazineTitleField.getText());
            returnSource.setWebsiteTitle(websiteTitleField.getText());
            returnSource.setVolume(Integer.parseInt(volumeField.getText()));
            returnSource.setEdition(Integer.parseInt(editionField.getText()));
            returnSource.setIssue(Integer.parseInt(issueField.getText()));
            returnSource.setPublisher(publisherField.getText());
            returnSource.setYearPublished(Integer.parseInt(yearPublishedField.getText()));
            returnSource.setDatePublished(datePublishedField.getText());
            returnSource.setURL(URLField.getText());
            returnSource.setVersion(Integer.parseInt(versionField.getText()));
            returnSource.setDatabase(databaseField.getText());
            returnSource.setDatabaseService(databaseServiceField.getText());
            returnSource.setMedium(mediumField.getText());
            returnSource.setPagesCitedStart(Integer.parseInt(pagesCited1Field.getText()));
            returnSource.setPagesCitedEnd(Integer.parseInt(pagesCited2Field.getText()));
            returnSource.setAnnotation(annotationField.getText());
            window.close();
        });

        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(5, 5, 5, 5));
        bottomBar.setSpacing(10);
        bottomBar.getChildren().addAll(saveJFXButton, closeJFXButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.getChildren().addAll(intro, inputVBox, bottomBar);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        closeJFXButton.requestFocus();
        window.showAndWait();
        return returnSource;
    }

    public static Source EditSource(Source originalSource) {//Opens a window to edit existing sources
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Source");
        window.setMinWidth(500);
        window.setMinHeight(720);
        window.setOnCloseRequest(e -> window.close());

        Label intro = new Label("Enter source information:");

        HBox titleLine = new HBox();
        titleLine.setPadding(new Insets(0, 5, 0, 5));
        titleLine.setSpacing(10);
        TextField titleField = new TextField(originalSource.title);
        titleField.setPromptText("");
        Label titleLabel = new Label("Title:");
        titleLabel.setMinSize(100,0);
        titleLine.getChildren().addAll(titleLabel, titleField);

        HBox authorLine = new HBox();
        authorLine.setPadding(new Insets(0, 5, 0, 5));
        authorLine.setSpacing(10);
        TextField authorField = new TextField(originalSource.author.toString());
        authorField.setPromptText("");
        Label authorLabel = new Label("Author:");
        authorLabel.setMinSize(100,0);
        authorLine.getChildren().addAll(authorLabel, authorField);

        HBox magazineTitleLine = new HBox();
        magazineTitleLine.setPadding(new Insets(0, 5, 0, 5));
        magazineTitleLine.setSpacing(10);
        TextField magazineTitleField = new TextField(originalSource.getMagazineTitle());
        magazineTitleField.setPromptText("");
        Label magazineTitleLabel = new Label("Magazine Title:");
        magazineTitleLabel.setMinSize(100,0);
        magazineTitleLine.getChildren().addAll(magazineTitleLabel, magazineTitleField);

        HBox websiteTitleLine = new HBox();
        websiteTitleLine.setPadding(new Insets(0, 5, 0, 5));
        websiteTitleLine.setSpacing(10);
        TextField websiteTitleField = new TextField(originalSource.getWebsiteTitle());
        websiteTitleField.setPromptText("");
        Label websiteTitleLabel = new Label("Website Title:");
        websiteTitleLabel.setMinSize(100,0);
        websiteTitleLine.getChildren().addAll(websiteTitleLabel, websiteTitleField);

        HBox volumeLine = new HBox();
        volumeLine.setPadding(new Insets(0, 5, 0, 5));
        volumeLine.setSpacing(10);
        TextField volumeField = new TextField(Integer.toString(originalSource.getVolume()));
        volumeField.setPromptText("");
        Label volumeLabel = new Label("Volume:");
        volumeLabel.setMinSize(100,0);
        volumeLine.getChildren().addAll(volumeLabel, volumeField);

        HBox editionLine = new HBox();
        editionLine.setPadding(new Insets(0, 5, 0, 5));
        editionLine.setSpacing(10);
        TextField editionField = new TextField(Integer.toString(originalSource.getEdition()));
        editionField.setPromptText("");
        Label editionLabel = new Label("Edition:");
        editionLabel.setMinSize(100,0);
        editionLine.getChildren().addAll(editionLabel, editionField);

        HBox issueLine = new HBox();
        issueLine.setPadding(new Insets(0, 5, 0, 5));
        issueLine.setSpacing(10);
        TextField issueField = new TextField(Integer.toString(originalSource.getIssue()));
        issueField.setPromptText("");
        Label issueLabel = new Label("Issue:");
        issueLabel.setMinSize(100,0);
        issueLine.getChildren().addAll(issueLabel, issueField);

        HBox publisherLine = new HBox();
        publisherLine.setPadding(new Insets(0, 5, 0, 5));
        publisherLine.setSpacing(10);
        TextField publisherField = new TextField(originalSource.getPublisher());
        publisherField.setPromptText("");
        Label publisherLabel = new Label("Publisher:");
        publisherLabel.setMinSize(100,0);
        publisherLine.getChildren().addAll(publisherLabel, publisherField);

        HBox yearPublishedLine = new HBox();
        yearPublishedLine.setPadding(new Insets(0, 5, 0, 5));
        yearPublishedLine.setSpacing(10);
        TextField yearPublishedField = new TextField(Integer.toString(originalSource.getYearPublished()));
        yearPublishedField.setPromptText("");
        Label yearPublishedLabel = new Label("Year Published:");
        yearPublishedLabel.setMinSize(100,0);
        yearPublishedLine.getChildren().addAll(yearPublishedLabel, yearPublishedField);

        HBox datePublishedLine = new HBox();
        datePublishedLine.setPadding(new Insets(0, 5, 0, 5));
        datePublishedLine.setSpacing(10);
        TextField datePublishedField = new TextField(originalSource.getDatePublished());
        datePublishedField.setPromptText("");
        Label datePublishedLabel = new Label("Date Published:");
        datePublishedLabel.setMinSize(100,0);
        datePublishedLine.getChildren().addAll(datePublishedLabel, datePublishedField);

        HBox URLLine = new HBox();
        URLLine.setPadding(new Insets(0, 5, 0, 5));
        URLLine.setSpacing(10);
        TextField URLField = new TextField(originalSource.getURL());
        URLField.setPromptText("");
        Label URLLabel = new Label("URL:");
        URLLabel.setMinSize(100,0);
        URLLine.getChildren().addAll(URLLabel, URLField);

        HBox versionLine = new HBox();
        versionLine.setPadding(new Insets(0, 5, 0, 5));
        versionLine.setSpacing(10);
        TextField versionField = new TextField(Integer.toString(originalSource.getVersion()));
        versionField.setPromptText("");
        Label versionLabel = new Label("Version:");
        versionLabel.setMinSize(100,0);
        versionLine.getChildren().addAll(versionLabel, versionField);

        HBox annotationLine = new HBox();
        annotationLine.setPadding(new Insets(0, 5, 0, 5));
        annotationLine.setSpacing(10);
        TextField annotationField = new TextField(originalSource.getAnnotation());
        annotationField.setPromptText("");
        Label annotationLabel = new Label("Annotation:");
        annotationLabel.setMinSize(100,0);
        annotationLine.getChildren().addAll(annotationLabel, annotationField);

        HBox databaseLine = new HBox();
        databaseLine.setPadding(new Insets(0, 5, 0, 5));
        databaseLine.setSpacing(10);
        TextField databaseField = new TextField(originalSource.getDatabase());
        databaseField.setPromptText("");
        Label databaseLabel = new Label("Database:");
        databaseLabel.setMinSize(100,0);
        databaseLine.getChildren().addAll(databaseLabel, databaseField);

        HBox databaseServiceLine = new HBox();
        databaseServiceLine.setPadding(new Insets(0, 5, 0, 5));
        databaseServiceLine.setSpacing(10);
        TextField databaseServiceField = new TextField(originalSource.getDatabaseService());
        databaseServiceField.setPromptText("");
        Label databaseServiceLabel = new Label("Database Service:");
        databaseServiceLabel.setMinSize(100,0);
        databaseServiceLine.getChildren().addAll(databaseServiceLabel, databaseServiceField);

        HBox mediumLine = new HBox();
        mediumLine.setPadding(new Insets(0, 5, 0, 5));
        mediumLine.setSpacing(10);
        TextField mediumField = new TextField(originalSource.getMedium());
        mediumField.setPromptText("");
        Label mediumLabel = new Label("Medium:");
        mediumLabel.setMinSize(100,0);
        mediumLine.getChildren().addAll(mediumLabel, mediumField);

        HBox pagesCitedLine = new HBox();
        pagesCitedLine.setPadding(new Insets(0, 5, 0, 5));
        pagesCitedLine.setSpacing(10);
        TextField pagesCited1Field = new TextField(Integer.toString(originalSource.getPagesCitedStart()));
        pagesCited1Field.setPromptText("");
        TextField pagesCited2Field = new TextField(Integer.toString(originalSource.getPagesCitedEnd()));
        pagesCited2Field.setPromptText("");
        Text dash = new Text("-");
        Label pagesCitedLabel = new Label("Pages Cited:");
        pagesCitedLabel.setMinSize(100,0);
        pagesCitedLine.getChildren().addAll(pagesCitedLabel, pagesCited1Field, dash, pagesCited2Field);

        VBox inputVBox = new VBox();
        inputVBox.setPadding(new Insets(5, 5, 5, 5));
        inputVBox.setSpacing(10);
        inputVBox.getChildren().addAll(titleLine, authorLine, magazineTitleLine, websiteTitleLine, volumeLine,
                editionLine, issueLine, publisherLine, yearPublishedLine, datePublishedLine, URLLine, versionLine,
                databaseLine, databaseServiceLine, mediumLine, pagesCitedLine, annotationLine);

        JFXButton closeJFXButton = new JFXButton("Quit");
        JFXButton saveJFXButton = new JFXButton("Save");
        closeJFXButton.setOnAction(e -> window.close());
        saveJFXButton.setOnAction(e -> {
            ArrayList<String> authorList = new ArrayList<>(Arrays.asList(authorField.getText().split("\\s*,\\s*")));
            returnSource = new Source(titleField.getText());
            returnSource.author = authorList;
            returnSource.setMagazineTitle(magazineTitleField.getText());
            returnSource.setWebsiteTitle(websiteTitleField.getText());
            returnSource.setVolume(Integer.parseInt(volumeField.getText()));
            returnSource.setEdition(Integer.parseInt(editionField.getText()));
            returnSource.setIssue(Integer.parseInt(issueField.getText()));
            returnSource.setPublisher(publisherField.getText());
            returnSource.setYearPublished(Integer.parseInt(yearPublishedField.getText()));
            returnSource.setDatePublished(datePublishedField.getText());
            returnSource.setURL(URLField.getText());
            returnSource.setVersion(Integer.parseInt(versionField.getText()));
            returnSource.setDatabase(databaseField.getText());
            returnSource.setDatabaseService(databaseServiceField.getText());
            returnSource.setMedium(mediumField.getText());
            returnSource.setPagesCitedStart(Integer.parseInt(pagesCited1Field.getText()));
            returnSource.setPagesCitedEnd(Integer.parseInt(pagesCited2Field.getText()));
            returnSource.setAnnotation(annotationField.getText());
            window.close();
        });

        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(5, 5, 5, 5));
        bottomBar.setSpacing(10);
        bottomBar.getChildren().addAll(saveJFXButton, closeJFXButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.getChildren().addAll(intro, titleField, authorField, bottomBar);
        layout.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        closeJFXButton.requestFocus();
        window.showAndWait();
        return returnSource;
    }

}
