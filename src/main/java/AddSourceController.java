import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Arrays;

public class AddSourceController {
    private Main mainWindow;

    @FXML
    TextField titleField;
    @FXML
    TextField authorField;
    @FXML
    TextField magazineTitleField;
    @FXML
    TextField websiteTitleField;
    @FXML
    TextField volumeField;
    @FXML
    TextField editionField;
    @FXML
    TextField issueField;
    @FXML
    TextField publisherField;
    @FXML
    TextField datePublishedField;
    @FXML
    TextField URLfield;
    @FXML
    TextField versionField;
    @FXML
    TextField databaseField;
    @FXML
    TextField databaseServiceField;
    @FXML
    TextField mediumField;
    @FXML
    TextField pagesCitedField1;
    @FXML
    TextField pagesCitedField2;
    @FXML
    TextField annotationField;

    public void SaveSource() {
        if (!titleField.getText().equals("")) {
            Source output = new Source(titleField.getText());
            //test
            /*
            if (authorField.getText() != "") {
                ArrayList<String> list1 = new ArrayList<>(Arrays.asList(authorField.getText().split("\\s*,\\s*")));
                ArrayList<StringProperty> list2 = new ArrayList<>();
                for (String s : list1) {
                    list2.add(new SimpleStringProperty(s));
                }
                output.setAuthor(list2);//yeesh
            }
            */
            if(!authorField.getText().equals("")) {
                output.setAuthor(authorField.getText());
            }
            if (!magazineTitleField.getText().equals("")) {
                output.setMagazineTitle(magazineTitleField.getText());
            }
            if (!websiteTitleField.getText().equals("")) {
                output.setWebsiteTitle(websiteTitleField.getText());
            }
            if (!volumeField.getText().equals("")) {
                output.setVolume(volumeField.getText());
            }
            if (!editionField.getText().equals("")) {
                output.setEdition(editionField.getText());
            }
            if (!issueField.getText().equals("")) {
                output.setIssue(issueField.getText());
            }
            if (!publisherField.getText().equals("")) {
                output.setPublisher(publisherField.getText());
            }
            if (!datePublishedField.getText().equals("")) {
                output.setDatePublished(datePublishedField.getText());
            }
            if (!URLfield.getText().equals("")) {
                output.setURL(URLfield.getText());
            }
            if (!versionField.getText().equals("")) {
                output.setVersion(versionField.getText());
            }
            if (!databaseField.getText().equals("")) {
                output.setDatabase(databaseField.getText());
            }
            if (!databaseServiceField.getText().equals("")) {
                output.setDatabaseService(databaseServiceField.getText());
            }
            if (!mediumField.getText().equals("")) {
                output.setMedium(mediumField.getText());
            }
            if (!annotationField.getText().equals("")) {
                output.setAnnotation(annotationField.getText());
            }
            if (!pagesCitedField1.getText().equals("")) {
                output.setPagesCitedStart(pagesCitedField1.getText());
            }
            if (!pagesCitedField2.getText().equals("")) {
                output.setPagesCitedEnd(pagesCitedField2.getText());
            }

            mainWindow.addSource(output);

            System.out.println(output.ToMLA());
            titleField.getScene().getWindow().hide();
        }
    }

    public void Close() {
        titleField.getScene().getWindow().hide();
    }

    public void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }
}
