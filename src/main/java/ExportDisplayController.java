import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExportDisplayController {

    public ListView<String> sourcesDisplayList;
    private ObservableList<Source> exportList;
    public Button clipboardButton;
    public Button exitButton;
    private boolean isAPA;

    private boolean isCustom = false;

    private StyleParser parser = new StyleParser();

    void initCustom(String fileName) throws IOException {
        isCustom = true;
        ObservableList<FieldStyle> customStyle = FXCollections.observableArrayList();
        File directory = new File("styles/");
        File fileBuffer = null;

        ObservableList<File> stylesData = FXCollections.observableArrayList();
        stylesData.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles())));

        for(File file:stylesData)
        {
            if(fileName.equals(file.getName()))
            {
                fileBuffer = file;
                break;
            }
        }

        if(fileBuffer != null)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            List<FieldStyle> buffer = objectMapper.readValue(fileBuffer, new TypeReference<List<FieldStyle>>(){});

            for(FieldStyle style:buffer)
            {
                style.open();
            }

            try
            {
                customStyle.addAll(buffer);
            }
            catch(Exception e)
            {
                //noinspection ThrowablePrintedToSystemOut
                System.out.println(e);
            }
        }

        parser.configureStyle(customStyle);
    }

    public void copyText(ActionEvent actionEvent) {
        StringBuilder stringBuilder = new StringBuilder();

        if(isCustom)
        {
            stringBuilder.append(parser.export(exportList));
        }
        else if(isAPA)
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

    void loadSources(ObservableList<Source> exportList, boolean isAPA) {
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
