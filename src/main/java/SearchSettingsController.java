import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;

public class SearchSettingsController {
    private Main mainWindow;
    private String url;
    private String port;

    @FXML
    TextField urlField;
    @FXML
    TextField portField;

    public void setMainWindow(Main mainWindow) {this.mainWindow = mainWindow;}

    public void SetDefaults(String u, String p){
        url = u;
        port = p;
        urlField.setText(url);
        portField.setText(port);
    }

    @FXML
    private void initialize() {

    }
    public void SaveAndExit(){
        this.mainWindow.searchURL = urlField.getText();
        this.mainWindow.searchPort = portField.getText();
        urlField.getScene().getWindow().hide();
    }
    public void Exit(){
        urlField.getScene().getWindow().hide();
    }
}
