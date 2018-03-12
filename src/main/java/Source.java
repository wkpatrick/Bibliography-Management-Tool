import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Source {

    StringProperty title;
    StringProperty author;
    StringProperty MagazineTitle;
    StringProperty WebsiteTitle;
    IntegerProperty Volume;
    IntegerProperty Edition;
    IntegerProperty Issue;
    StringProperty Publisher;
    IntegerProperty YearPublished;
    StringProperty DatePublished;
    StringProperty URL;
    IntegerProperty Version;
    StringProperty Annotation;
    StringProperty Database;
    StringProperty DatabaseService;
    StringProperty Medium;
    IntegerProperty PagesCitedStart;
    IntegerProperty PagesCitedEnd;

    public Source(String sourceTitle) {
        title = new SimpleStringProperty("");
        title.set(sourceTitle);
        author = new SimpleStringProperty("");
        MagazineTitle = new SimpleStringProperty("");
        WebsiteTitle = new SimpleStringProperty("");
        Volume = new SimpleIntegerProperty(0);
        Edition = new SimpleIntegerProperty(0);
        Issue = new SimpleIntegerProperty(0);
        Publisher = new SimpleStringProperty("");
        YearPublished = new SimpleIntegerProperty(0);
        DatePublished = new SimpleStringProperty("");
        URL = new SimpleStringProperty("");
        Version = new SimpleIntegerProperty(0);
        Annotation = new SimpleStringProperty("");
        Database = new SimpleStringProperty("");
        DatabaseService = new SimpleStringProperty("");
        Medium = new SimpleStringProperty("");
        PagesCitedStart = new SimpleIntegerProperty(0);
        PagesCitedEnd = new SimpleIntegerProperty(0);
    }

/*
    public void AddAuthor(String newAuthor) {
        author.add(new SimpleStringProperty(newAuthor));
    }

    public void RemoveAuthor(String badAuthor) {
        for (StringProperty s : author) {
            if (s.get().equals(badAuthor)) {
                author.remove(s);
                break;
            }
        }
    }
*/

    public String ToMLA() {
        String finalOutput = "";
        /*
        if (!author.isEmpty()) {
            String authorString = "";
            for (StringProperty s : author) {
                authorString += s.get() + ", ";
            }
            authorString = authorString.substring(0, authorString.length() - 2);
            finalOutput += authorString + ". ";
        }
        */
        if (!author.get().equals("")) {
            finalOutput += author.get() + ". ";
        }
        if (!title.get().equals("")) {
            finalOutput += title.get() + ". ";
        }
        if (!MagazineTitle.get().equals("")) {
            finalOutput += MagazineTitle.get() + ". ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += WebsiteTitle.get() + ". ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database.get() + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService.get() + ". ";
        }
        if (Version.get() != 0) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (Volume.get() != 0) {
            finalOutput += Volume.get() + ", ";
        }
        if (Edition.get() != 0) {
            finalOutput += Edition.get() + ", ";
        }
        if (Issue.get() != 0) {
            finalOutput += Issue.get() + ", ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher.get() + ", ";
        }
        if (YearPublished.get() != 0) {
            finalOutput += YearPublished.get() + ", ";
        }
        if (!DatePublished.get().equals("")) {
            finalOutput += DatePublished.get() + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += Medium.get() + ", ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL.get() + ", ";
        }
        if (PagesCitedStart.get() != 0 && PagesCitedEnd.get() != 0) {
            if (PagesCitedEnd.get() > PagesCitedStart.get()) {
                finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
            } else {
                finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation.get() + ", ";
        }
        finalOutput = finalOutput.substring(0, finalOutput.length() - 2);
        return finalOutput;
    }

    public String ToAPA()//APA wants some things in italics. Could be an issue.
    {
        String finalOutput = "";
        /*
        if (!author.isEmpty()) {
            String authorString = "";
            for (StringProperty s : author) {
                authorString += s.get() + ", ";
            }
            authorString = authorString.substring(0, authorString.length() - 2);
            finalOutput += authorString + ". ";
        }
        */

        if(!author.get().equals("")){
            finalOutput += author.get() + ". ";
        }
        if (!DatePublished.get().equals("")) {
            finalOutput += "(" + DatePublished.get() + ")" + ", ";
        } else {
            finalOutput += "n.d." + ", ";
        }
        if (!title.get().equals("")) {
            finalOutput += title.get() + ". ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher.get() + ", ";
        }
        if (!MagazineTitle.get().equals("")) {
            finalOutput += MagazineTitle.get() + ". ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += WebsiteTitle.get() + ". ";
        }
        if (Volume.get() != 0) {
            finalOutput += Volume.get() + ", ";
        }
        if (Edition.get() != 0) {
            finalOutput += Edition.get() + ", ";
        }
        if (Issue.get() != 0) {
            finalOutput += "(" + Issue.get() + ")" + ", ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database.get() + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService.get() + ". ";
        }
        if (Version.get() != 0) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += Medium.get() + ", ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL.get() + ", ";
        }
        if (PagesCitedStart.get() != 0 && PagesCitedEnd.get() != 0) {
            if (PagesCitedEnd.get() > PagesCitedStart.get()) {
                finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
            } else {
                finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation.get() + ", ";
        }
        finalOutput = finalOutput.substring(0, finalOutput.length() - 2);
        return finalOutput;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    /*
    public List<StringProperty> getAuthor() {
        return author;
    }

    public void setAuthor(List<StringProperty> author) {
        this.author = author;
    }
    */

    public String getAuthor()
    {
        return author.get();
    }

    public void setAuthor(String author)
    {
        this.author.set(author);
    }

    public String getMagazineTitle() {
        return MagazineTitle.get();
    }

    public StringProperty magazineTitleProperty() {
        return MagazineTitle;
    }

    public void setMagazineTitle(String magazineTitle) {
        this.MagazineTitle.set(magazineTitle);
    }

    public String getWebsiteTitle() {
        return WebsiteTitle.get();
    }

    public StringProperty websiteTitleProperty() {
        return WebsiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.WebsiteTitle.set(websiteTitle);
    }

    public int getVolume() {
        return Volume.get();
    }

    public IntegerProperty volumeProperty() {
        return Volume;
    }

    public void setVolume(int volume) {
        this.Volume.set(volume);
    }

    public int getEdition() {
        return Edition.get();
    }

    public IntegerProperty editionProperty() {
        return Edition;
    }

    public void setEdition(int edition) {
        this.Edition.set(edition);
    }

    public int getIssue() {
        return Issue.get();
    }

    public IntegerProperty issueProperty() {
        return Issue;
    }

    public void setIssue(int issue) {
        this.Issue.set(issue);
    }

    public String getPublisher() {
        return Publisher.get();
    }

    public StringProperty publisherProperty() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        this.Publisher.set(publisher);
    }

    public int getYearPublished() {
        return YearPublished.get();
    }

    public IntegerProperty yearPublishedProperty() {
        return YearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.YearPublished.set(yearPublished);
    }

    public String getDatePublished() {
        return DatePublished.get();
    }

    public StringProperty datePublishedProperty() {
        return DatePublished;
    }

    public void setDatePublished(String datePublished) {
        this.DatePublished.set(datePublished);
    }

    public String getURL() {
        return URL.get();
    }

    public StringProperty URLProperty() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL.set(URL);
    }

    public int getVersion() {
        return Version.get();
    }

    public IntegerProperty versionProperty() {
        return Version;
    }

    public void setVersion(int version) {
        this.Version.set(version);
    }

    public String getAnnotation() {
        return Annotation.get();
    }

    public StringProperty annotationProperty() {
        return Annotation;
    }

    public void setAnnotation(String annotation) {
        this.Annotation.set(annotation);
    }

    public String getDatabase() {
        return Database.get();
    }

    public StringProperty databaseProperty() {
        return Database;
    }

    public void setDatabase(String database) {
        this.Database.set(database);
    }

    public String getDatabaseService() {
        return DatabaseService.get();
    }

    public StringProperty databaseServiceProperty() {
        return DatabaseService;
    }

    public void setDatabaseService(String databaseService) {
        this.DatabaseService.set(databaseService);
    }

    public String getMedium() {
        return Medium.get();
    }

    public StringProperty mediumProperty() {
        return Medium;
    }

    public void setMedium(String medium) {
        this.Medium.set(medium);
    }

    public int getPagesCitedStart() {
        return PagesCitedStart.get();
    }

    public IntegerProperty pagesCitedStartProperty() {
        return PagesCitedStart;
    }

    public void setPagesCitedStart(int pagesCitedStart) {
        this.PagesCitedStart.set(pagesCitedStart);
    }

    public int getPagesCitedEnd() {
        return PagesCitedEnd.get();
    }

    public IntegerProperty pagesCitedEndProperty() {
        return PagesCitedEnd;
    }

    public void setPagesCitedEnd(int pagesCitedEnd) {
        this.PagesCitedEnd.set(pagesCitedEnd);
    }
}
