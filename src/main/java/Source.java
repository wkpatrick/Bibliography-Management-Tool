import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Source {

    StringProperty title;
    List<StringProperty> author;
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
        title = new SimpleStringProperty();
        title.set(sourceTitle);
        MagazineTitle = new SimpleStringProperty();
        WebsiteTitle = new SimpleStringProperty();
        Volume = new SimpleIntegerProperty();
        Edition = new SimpleIntegerProperty();
        Issue = new SimpleIntegerProperty();
        Publisher = new SimpleStringProperty();
        YearPublished = new SimpleIntegerProperty();
        DatePublished = new SimpleStringProperty();
        URL = new SimpleStringProperty();
        Version = new SimpleIntegerProperty();
        Annotation = new SimpleStringProperty();
        Database = new SimpleStringProperty();
        DatabaseService = new SimpleStringProperty();
        Medium = new SimpleStringProperty();
        PagesCitedStart = new SimpleIntegerProperty();
        PagesCitedEnd = new SimpleIntegerProperty();
    }

    public void AddAuthor(String newAuthor){
        author.add(new SimpleStringProperty(newAuthor));
    }

    public void RemoveAuthor(String badAuthor){
        for (StringProperty s: author) {
            if(s.get().equals(badAuthor)){
                author.remove(s);
                break;
            }
        }
    }

    public String ToMLA() {
        String finalOutput = "";
        if (!author.isEmpty()) {
            String authorString = author.toString();
            authorString.replace("[", "");
            authorString.replace("]", "");
            finalOutput += authorString + ". ";
        }
        if (!title.get().equals("")) {
            finalOutput += title + ". ";
        }
        if (!MagazineTitle.get().equals("")) {
            finalOutput += MagazineTitle + ". ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += WebsiteTitle + ". ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService + ". ";
        }
        if (Version.get() != 0) {
            finalOutput += "ver." + Version + ", ";
        }
        if (Volume.get() != 0) {
            finalOutput += Volume + ", ";
        }
        if (Edition.get() != 0) {
            finalOutput += Edition + ", ";
        }
        if (Issue.get() != 0) {
            finalOutput += Issue + ", ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher + ", ";
        }
        if (YearPublished.get() != 0) {
            finalOutput += YearPublished + ", ";
        }
        if (!DatePublished.get().equals("")) {
            finalOutput += DatePublished + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += Medium + ", ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL + ", ";
        }
        if (PagesCitedStart.get() != 0 && PagesCitedEnd.get() != 0) {
            if (PagesCitedEnd.get() > PagesCitedStart.get()) {
                finalOutput += "pp " + PagesCitedStart + "-" + PagesCitedEnd + ", ";
            } else {
                finalOutput += "pp " + PagesCitedStart + "-" + "end" + ", ";
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation + ", ";
        }
        finalOutput = finalOutput.substring(0, finalOutput.length() - 2);
        return finalOutput;
    }

    public String ToAPA()//APA wants some things in italics. Could be an issue.
    {
        String finalOutput = "";
        if (!author.isEmpty()) {
            String authorString = author.toString();
            authorString = authorString.replace("[", "");
            authorString = authorString.replace("]", "");
            finalOutput += authorString + ". ";
        }
        if (!DatePublished.get().equals("")) {
            finalOutput += "(" + DatePublished + ")" + ", ";
        } else {
            finalOutput += "n.d." + ", ";
        }
        if (!title.get().equals("")) {
            finalOutput += title + ". ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher + ", ";
        }
        if (!MagazineTitle.get().equals("")) {
            finalOutput += MagazineTitle + ". ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += WebsiteTitle + ". ";
        }
        if (Volume.get() != 0) {
            finalOutput += Volume + ", ";
        }
        if (Edition.get() != 0) {
            finalOutput += Edition + ", ";
        }
        if (Issue.get() != 0) {
            finalOutput += "(" + Issue + ")" + ", ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService + ". ";
        }
        if (Version.get() != 0) {
            finalOutput += "ver." + Version + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += Medium + ", ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL + ", ";
        }
        if (PagesCitedStart.get() != 0 && PagesCitedEnd.get() != 0) {
            if (PagesCitedEnd.get() > PagesCitedStart.get()) {
                finalOutput += "pp " + PagesCitedStart + "-" + PagesCitedEnd + ", ";
            } else {
                finalOutput += "pp " + PagesCitedStart + "-" + "end" + ", ";
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation + ", ";
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

    public List<StringProperty> getAuthor() {
        return author;
    }

    public void setAuthor(List<StringProperty> author) {
        this.author = author;
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
