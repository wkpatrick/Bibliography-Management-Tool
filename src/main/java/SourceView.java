import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Arrays;

public class SourceView {

    @FXML AnchorPane anchor;

    @FXML TextField titleField;
    @FXML TextField authorField;
    @FXML TextField magazineTitleField;
    @FXML TextField websiteTitleField;
    @FXML TextField volumeField;
    @FXML TextField editionField;
    @FXML TextField issueField;
    @FXML TextField publisherField;
    @FXML TextField yearPublishedField;
    @FXML TextField datePublishedField;
    @FXML TextField URLfield;
    @FXML TextField versionField;
    @FXML TextField databaseField;
    @FXML TextField databaseServiceField;
    @FXML TextField mediumField;
    @FXML TextField pagesCitedField1;
    @FXML TextField pagesCitedField2;
    @FXML TextField annotationField;

    public void SaveSource(){
        if(!titleField.getText().equals("")){
            Source output =  new Source(titleField.getText());
            if(authorField.getText() != ""){
                ArrayList<String> list1 = new ArrayList<>(Arrays.asList(authorField.getText().split("\\s*,\\s*")));
                ArrayList<StringProperty> list2 = new ArrayList<>();
                for (String s: list1) {
                    list2.add(new SimpleStringProperty(s));
                }
                output.setAuthor(list2);//yeesh
            }
            if(!magazineTitleField.getText().equals("")){
                output.setMagazineTitle(magazineTitleField.getText());
            }
            if(!websiteTitleField.getText().equals("")){
                output.setWebsiteTitle(websiteTitleField.getText());
            }
            if(!volumeField.getText().equals("")){
                output.setVolume(Integer.parseInt(volumeField.getText()));
            }
            if(!editionField.getText().equals("")){
                output.setEdition(Integer.parseInt(editionField.getText()));
            }
            if(!issueField.getText().equals("")){
                output.setIssue(Integer.parseInt(issueField.getText()));
            }
            if(!publisherField.getText().equals("")){
                output.setPublisher(publisherField.getText());
            }
            if(!yearPublishedField.getText().equals("")){
                output.setYearPublished(Integer.parseInt(yearPublishedField.getText()));
            }
            if(!datePublishedField.getText().equals("")){
                output.setDatePublished(datePublishedField.getText());
            }
            if(!URLfield.getText().equals("")){
                output.setURL(URLfield.getText());
            }
            if(!versionField.getText().equals("")){
                output.setVersion(Integer.parseInt(versionField.getText()));
            }
            if(!databaseField.getText().equals("")){
                output.setDatabase(databaseField.getText());
            }
            if(!databaseServiceField.getText().equals("")){
                output.setDatabaseService(databaseServiceField.getText());
            }
            if(!mediumField.getText().equals("")){
                output.setMedium(mediumField.getText());
            }
            if(!annotationField.getText().equals("")){
                output.setAnnotation(annotationField.getText());
            }
            if(!pagesCitedField1.getText().equals("")){
                output.setPagesCitedStart(Integer.parseInt(pagesCitedField1.getText()));
            }
            if(!pagesCitedField2.getText().equals("")){
                output.setPagesCitedEnd(Integer.parseInt(pagesCitedField2.getText()));
            }
            MainWindowController.sourceList.add(output);
            titleField.getScene().getWindow().hide();
        }
    }
    public void Close(){
        titleField.getScene().getWindow().hide();
    }
}
