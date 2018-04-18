import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Field;
import java.util.ArrayList;

class StyleParser
{
    ObservableList<FieldStyle> citeFields = FXCollections.observableArrayList();
    private ObservableList<Source> selectedSources = FXCollections.observableArrayList();
    private ObservableList<FieldStyle> selectedFields = FXCollections.observableArrayList();

    //Fieldname type enum dictionary
    //DO NOT ERASE YearPublished
    public enum FieldNames
    {
        author, title, MagazineTitle, WebsiteTitle,
        Database, DatabaseService, Version, Volume,
        Edition, Issue, Publisher, DatePublished, Medium,
        URL, PagesCitedStart, PagesCitedEnd, YearPublished
    }

    //Default configuration is MLA.
    StyleParser()
    {
        //MLA configuration
        FieldNames fieldnamesMLA[] = {FieldNames.author, FieldNames.title, FieldNames.MagazineTitle, FieldNames.WebsiteTitle,
                FieldNames.Database, FieldNames.DatabaseService, FieldNames.Version, FieldNames.Volume,
                FieldNames.Edition, FieldNames.Issue, FieldNames.Publisher,
                FieldNames.DatePublished, FieldNames.Medium, FieldNames.URL, FieldNames.PagesCitedStart,
                FieldNames.PagesCitedEnd};

        //Populate FieldStyles
        for (FieldNames field : fieldnamesMLA)
        {
            //Initialize FieldStyles with string value of enum.
            citeFields.add(new FieldStyle(field.name()));
        }
    }

    void configureStyle(ObservableList<FieldStyle> inputStyles)
    {
        citeFields.clear();
        citeFields.addAll(inputStyles);
    }

    //Repopulates lists of selected sources and citefields
    private void updateSelection(ObservableList<Source> inputSources)
    {
        selectedSources.clear();
        selectedFields.clear();

        for(Source source:inputSources)
        {
            if(source.isSelected().getValue())
            {
                selectedSources.add(source);
            }
        }

        for(FieldStyle style:citeFields)
        {
            if(style.selected.get())
            {
                selectedFields.add(style);
            }
        }
    }

    //Applies HTML tags according specified style config
    private String format(String field, FieldStyle style){
        String result = field;

        if(field.isEmpty())
            return ("");

        if(style.quoted.get()) {
            result = '"' + result + '"';
        }
        if(style.italicised.get()){
            result = "<i>" + result + "</i>";
        }
        if(style.bold.get()){
            result = "<b>" + result + "</b>";
        }
        if(style.underlined.get()){
            result = "<u>" + result + "</u>";
        }

        return result;
    }

    //Switch statement for field specification, returns formatted field.
    private String getFormattedField(FieldStyle style, Source source)
    {
        String bufferField = "";

        try
        {
            switch(FieldNames.valueOf(style.fieldName.getValue()))
            {
                case author:
                    bufferField = format(source.getAuthor(), style);
                    break;
                case title:
                    bufferField = format(source.getTitle(), style);
                    break;
                case MagazineTitle:
                    bufferField = format(source.getMagazineTitle(), style);
                    break;
                case WebsiteTitle:
                    bufferField = format(source.getWebsiteTitle(), style);
                    break;
                case Database:
                    bufferField = format(source.getDatabase(), style);
                    break;
                case DatabaseService:
                    bufferField = format(source.getDatabaseService(), style);
                    break;
                case Version:
                    bufferField = format(source.getVersion(), style);
                    break;
                case Volume:
                    bufferField = format(source.getVolume(), style);
                    break;
                case Edition:
                    bufferField = format(source.getEdition(), style);
                    break;
                case Issue:
                    bufferField = format(source.getIssue(), style);
                    break;
                case Publisher:
                    bufferField = format(source.getPublisher(), style);
                    break;
                case DatePublished:
                    bufferField = format(source.getDatePublished(), style);
                    break;
                case Medium:
                    bufferField = format(source.getMedium(), style);
                    break;
                case URL:
                    bufferField = format(source.getURL(), style);
                    break;
                case PagesCitedStart:
                    bufferField = format(source.getPagesCitedStart(), style);
                    break;
                case PagesCitedEnd:
                    bufferField = format(source.getPagesCitedEnd(), style);
                    break;
                default:
                     break;
            }
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace();
        }

        return bufferField;
    }

    String export(ObservableList<Source> inputSources)
    {
        updateSelection(inputSources);

        StringBuilder result = new StringBuilder();
        String bufferField = "";

        for (Source source:selectedSources)
        {
            for(FieldStyle style:selectedFields)
            {
                bufferField = getFormattedField(style, source);
                if(!bufferField.isEmpty())
                {
                    result.append(bufferField);
                    result.append(". ");
                }
            }

            result.append("<br><br>");
        }

        return(result.toString());
    }
}