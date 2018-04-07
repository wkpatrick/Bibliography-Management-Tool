import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.*;

public class CiteStyleController {
    public Button deleteButton;
    private ObservableList<File> stylesData = FXCollections.observableArrayList();

    public Button shiftUpButton;
    public Button shiftDownButton;
    private Main mainWindow;
    public TableView<FieldStyle> createTable;
    public Button saveButton;
    public Button cancelButton;
    public ListView<String> styleListView;
    public Button editButton;

    private StyleParser parser = new StyleParser();

    private TableColumn<FieldStyle, Boolean> selectColumn = new TableColumn<>( "Select" );
    private TableColumn<FieldStyle, Boolean> italicsColumn = new TableColumn<>( "Italics" );
    private TableColumn<FieldStyle, Boolean> quotesColumn = new TableColumn<>( "Quotes" );
    private TableColumn<FieldStyle, Boolean> underlineColumn = new TableColumn<>( "Underline" );
    private TableColumn<FieldStyle, Boolean> boldColumn = new TableColumn<>( "Bold" );

    void setMainWindow(Main mainWindow) {
        this.mainWindow = mainWindow;
    }

    void init() {
        shiftUpButton.setMaxHeight(createTable.getHeight()/2);
        shiftUpButton.setMaxWidth(createTable.getWidth());
        shiftDownButton.setMaxHeight(createTable.getHeight()/2);
        shiftDownButton.setMaxWidth(createTable.getWidth());

        createTable.setEditable(true);
        selectColumn.setSortable(false);
        selectColumn.setCellValueFactory( f -> f.getValue().isSelected());
        selectColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        italicsColumn.setSortable(false);
        italicsColumn.setCellValueFactory( f -> f.getValue().isItalicised());
        italicsColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        quotesColumn.setSortable(false);
        quotesColumn.setCellValueFactory( f -> f.getValue().isQuoted());
        quotesColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        underlineColumn.setSortable(false);
        underlineColumn.setCellValueFactory( f -> f.getValue().isUnderlined());
        underlineColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        boldColumn.setSortable(false);
        boldColumn.setCellValueFactory( f -> f.getValue().isBold());
        boldColumn.setCellFactory( tc -> new CheckBoxTableCell<>());

        createTable.getColumns().add(selectColumn);
        createTable.getColumns().add(italicsColumn);
        createTable.getColumns().add(quotesColumn);
        createTable.getColumns().add(underlineColumn);
        createTable.getColumns().add(boldColumn);

        ObservableList<FieldStyle> data = createTable.getItems();

        try
        {
            data.addAll(parser.citeFields);
        }
        catch(Exception e)
        {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(e);
        }

        try
        {
            refreshStyleList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createStyle(ActionEvent actionEvent) throws IOException {
        ObservableList<FieldStyle> data = createTable.getItems();

        for(FieldStyle style:data)
        {
            style.save();
        }

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Enter Style Name");
        dialog.setHeaderText("Please enter a name for your custom style.");
        dialog.setContentText("Style name:");
        Optional<String> result = dialog.showAndWait();

        if(result.isPresent())
        {
            String pathname = "styles/" + result.get() + ".style";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(pathname), data);
        }

        refreshStyleList();
    }

    private void refreshStyleList() throws IOException {
        styleListView.getItems().clear();
        stylesData.clear();

        File directory = new File("styles/");
        stylesData.addAll(Arrays.asList(Objects.requireNonNull(directory.listFiles())));

        for(File file:stylesData)
        {
            styleListView.getItems().add(file.getName());
        }
    }

    public void exit(ActionEvent actionEvent) {
        cancelButton.getScene().getWindow().hide();
    }

    public void shiftUp(ActionEvent actionEvent) {
        FieldStyle buffer;
        int selectedIndex = createTable.getSelectionModel().getSelectedIndex();

        if(selectedIndex > 0)
        {
            buffer = createTable.getItems().get(selectedIndex);
            createTable.getItems().remove(selectedIndex);
            createTable.getItems().add(selectedIndex-1, buffer);
            createTable.getSelectionModel().select(selectedIndex-1);
        }
    }

    public void shiftDown(ActionEvent actionEvent) {
        FieldStyle buffer;
        int selectedIndex = createTable.getSelectionModel().getSelectedIndex();

        if(selectedIndex < createTable.getItems().size()-1)
        {
            buffer = createTable.getItems().get(selectedIndex);
            createTable.getItems().remove(selectedIndex);
            createTable.getItems().add(selectedIndex+1, buffer);
            createTable.getSelectionModel().select(selectedIndex+1);
        }
    }

    public void editStyle(ActionEvent actionEvent) throws IOException {
        String selection = styleListView.getSelectionModel().getSelectedItem();

        if(selection == null)
            return;

        File fileBuffer = null;
        for(File file:stylesData)
        {
            if(selection.equals(file.getName()))
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

            createTable.getItems().clear();
            ObservableList<FieldStyle> data = createTable.getItems();

            try
            {
                data.addAll(buffer);
            }
            catch(Exception e)
            {
                //noinspection ThrowablePrintedToSystemOut
                System.out.println(e);
            }
        }
    }

    public void deleteStyle(ActionEvent actionEvent) throws IOException {
        File fileBuffer = null;
        String selection = styleListView.getSelectionModel().getSelectedItem();

        if(selection == null)
            return;

        for(File file:stylesData)
        {
            if(selection.equals(file.getName()))
            {
                fileBuffer = file;
                break;
            }
        }

        if(fileBuffer != null)
        {
            if(fileBuffer.delete())
               refreshStyleList();
        }
    }
}