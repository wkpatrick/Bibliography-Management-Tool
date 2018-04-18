import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
        urlField.setOnKeyTyped((KeyEvent e) -> this.mainWindow.searchURL = urlField.getText());
        portField.setOnKeyTyped((KeyEvent e) -> this.mainWindow.searchPort = portField.getText());
    }
}
