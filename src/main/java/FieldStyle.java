import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FieldStyle
{
    StringProperty fieldName;
    BooleanProperty selected;
    BooleanProperty italicised;
    BooleanProperty quoted;
    BooleanProperty underlined;
    BooleanProperty bold;

    private String fieldNameJSON;
    private boolean selectedJSON;
    private boolean italicisedJSON;
    private boolean quotedJSON;
    private boolean underlinedJSON;
    private boolean boldJSON;

    public FieldStyle()
    {
        this("");
    }

    FieldStyle(String input){
        fieldName = new SimpleStringProperty("");
        fieldName.setValue(input);
        selected = new SimpleBooleanProperty(true);
        italicised = new SimpleBooleanProperty(false);
        quoted = new SimpleBooleanProperty(false);
        underlined = new SimpleBooleanProperty(false);
        bold = new SimpleBooleanProperty(false);

        fieldNameJSON = "";
        selectedJSON = true;
        italicisedJSON = false;
        quotedJSON = false;
        underlinedJSON = false;
        boldJSON = false;
    }

    void save()
    {
        fieldNameJSON = fieldName.get();
        selectedJSON = selected.get();
        italicisedJSON = italicised.get();
        quotedJSON = quoted.get();
        underlinedJSON = underlined.get();
        boldJSON = bold.get();
    }

    void open()
    {
        fieldName.setValue(fieldNameJSON);
        selected.setValue(selectedJSON);
        italicised.setValue(italicisedJSON);
        quoted.setValue(quotedJSON);
        underlined.setValue(underlinedJSON);
        bold.setValue(boldJSON);
    }

    //Get Methods for TableView Serialization
    public StringProperty fieldNameProperty()
    {
        return fieldName;
    }

    BooleanProperty isSelected()
    {
        return selected;
    }

    BooleanProperty isItalicised()
    {
        return italicised;
    }

    BooleanProperty isQuoted()
    {
        return quoted;
    }

    BooleanProperty isUnderlined()
    {
        return underlined;
    }

    BooleanProperty isBold()
    {
        return bold;
    }
    //End Get Methods for TableView Serialization

    //Get Methods for JSON Serialization
    public String getfieldNameJSON()
    {
        return fieldNameJSON;
    }

    public boolean getselectedJSON()
    {
        return selectedJSON;
    }

    public boolean getitalicisedJSON()
    {
        return italicisedJSON;
    }

    public boolean getquotedJSON()
    {
        return quotedJSON;
    }

    public boolean getunderlinedJSON()
    {
        return underlinedJSON;
    }

    public boolean getboldJSON()
    {
        return boldJSON;
    }

    //End Get Methods for JSON Serialization
}
