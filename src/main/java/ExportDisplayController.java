import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ExportDisplayController {

    public ListView<String> sourcesDisplayList;
    private ObservableList<Source> exportList;
    public Button clipboardButton;
    public Button exitButton;
    private boolean isAPA;

    public void copyText(ActionEvent actionEvent) {
        StringBuilder stringBuilder = new StringBuilder();

        if(isAPA)
        {
            for (Source source:exportList)
            {
                stringBuilder.append(source.ToAPAHtml());
                stringBuilder.append("<br><br>");
            }
        }
        else
        {
            for (Source source:exportList)
            {
                stringBuilder.append(source.ToMLAHtml());
                stringBuilder.append("<br><br>");
            }
        }

        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putHtml(stringBuilder.toString());
        clipboard.setContent(content);
    }

    public void exit(ActionEvent actionEvent) {
        sourcesDisplayList.getScene().getWindow().hide();
    }

    public void loadSources(ObservableList<Source> exportList, boolean isAPA) {
        int index = 0;

        if(isAPA)
        {
            for (Source source:exportList)
            {
                sourcesDisplayList.getItems().add(index,source.ToAPA());
            }
        }
        else
        {
            for (Source source:exportList)
            {
                sourcesDisplayList.getItems().add(index,source.ToMLA());
            }
        }

        this.exportList = exportList;
        this.isAPA = isAPA;
    }
}
