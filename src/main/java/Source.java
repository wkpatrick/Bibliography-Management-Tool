import javafx.beans.property.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class Source {

    StringProperty title;
    StringProperty author;
    StringProperty MagazineTitle;
    StringProperty WebsiteTitle;
    StringProperty Volume;
    StringProperty Edition;
    StringProperty Issue;
    StringProperty Publisher;
    StringProperty YearPublished;
    StringProperty DatePublished;
    StringProperty URL;
    StringProperty Version;
    StringProperty Annotation;
    StringProperty Database;
    StringProperty DatabaseService;
    StringProperty Medium;
    StringProperty PagesCitedStart;
    StringProperty PagesCitedEnd;

    BooleanProperty Selected;

    public Source(String sourceTitle) {
        title = new SimpleStringProperty("");
        title.set(sourceTitle);
        author = new SimpleStringProperty("");
        MagazineTitle = new SimpleStringProperty("");
        WebsiteTitle = new SimpleStringProperty("");
        Volume = new SimpleStringProperty("");
        Edition = new SimpleStringProperty("");
        Issue = new SimpleStringProperty("");
        Publisher = new SimpleStringProperty("");
        YearPublished = new SimpleStringProperty("");
        DatePublished = new SimpleStringProperty("");
        URL = new SimpleStringProperty("");
        Version = new SimpleStringProperty("");
        Annotation = new SimpleStringProperty("");
        Database = new SimpleStringProperty("");
        DatabaseService = new SimpleStringProperty("");
        Medium = new SimpleStringProperty("");
        PagesCitedStart = new SimpleStringProperty("");
        PagesCitedEnd = new SimpleStringProperty("");
        Selected = new SimpleBooleanProperty(true);
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
    public String ToMLAHtml() {
        String finalOutput = "";

        if (!author.get().equals("")) {
            finalOutput += author.get() + ". ";
        }
        if (!title.get().equals("")) {
            finalOutput += "<i>" + title.get() + "</i>. ";
        }
        if (!MagazineTitle.get().equals("")) {
            finalOutput += "\"" + MagazineTitle.get() + "\". ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += "<i>" + WebsiteTitle.get() + "</i>. ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database.get() + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService.get() + ". ";
        }
        if (!Version.get().equals("")) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (!Volume.get().equals("")) {
            finalOutput += Volume.get() + ", ";
        }
        if (!Edition.get().equals("")) {
            finalOutput += Edition.get() + ", ";
        }
        if (!Issue.get().equals("")) {
            finalOutput += Issue.get() + ", ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher.get() + ", ";
        }
        if (!YearPublished.get().equals("")) {
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
        if (!PagesCitedStart.get().equals("") && !PagesCitedEnd.get().equals("")) {
            try {
                if (Integer.parseInt(PagesCitedEnd.get()) > Integer.parseInt(PagesCitedStart.get())) {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
                } else {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
                }
            } catch (Exception e) {
                System.out.println("Pages cited formatting error!");
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation.get() + ", ";
        }
        finalOutput = finalOutput.substring(0, finalOutput.length() - 2);

        return finalOutput;
    }

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
        if (!Version.get().equals("")) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (!Volume.get().equals("")) {
            finalOutput += Volume.get() + ", ";
        }
        if (!Edition.get().equals("")) {
            finalOutput += Edition.get() + ", ";
        }
        if (!Issue.get().equals("")) {
            finalOutput += Issue.get() + ", ";
        }
        if (!Publisher.get().equals("")) {
            finalOutput += Publisher.get() + ", ";
        }
        if (!YearPublished.get().equals("")) {
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
        if (!PagesCitedStart.get().equals("") && !PagesCitedEnd.get().equals("")) {
            try {
                if (Integer.parseInt(PagesCitedEnd.get()) > Integer.parseInt(PagesCitedStart.get())) {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
                } else {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
                }
            } catch (Exception e) {
                System.out.println("Pages cited formatting error!");
            }
        }
        if (!Annotation.get().equals("")) {
            finalOutput += Annotation.get() + ", ";
        }
        finalOutput = finalOutput.substring(0, finalOutput.length() - 2);
        return finalOutput;
    }

    public String ToAPAHtml() {
        String finalOutput = "";

        if (!author.get().equals("")) {
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
            finalOutput += "<i>" + MagazineTitle.get() + "</i>. ";
        }
        if (!WebsiteTitle.get().equals("")) {
            finalOutput += WebsiteTitle.get() + ". ";
        }
        if (!Volume.get().equals("")) {
            finalOutput += "<i>" + Volume.get() + "</i>, ";
        }
        if (!Edition.get().equals("")) {
            finalOutput += "<i>" + Edition.get() + "</i>, ";
        }
        if (!Issue.get().equals("")) {
            finalOutput += "(" + Issue.get() + ")" + ", ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database.get() + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService.get() + ". ";
        }
        if (!Version.get().equals("")) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += "[" + Medium.get() + "], ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL.get() + ", ";
        }
        if (!PagesCitedStart.get().equals("") && !PagesCitedEnd.get().equals("")) {
            try {
                if (Integer.parseInt(PagesCitedEnd.get()) > Integer.parseInt(PagesCitedStart.get())) {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
                } else {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
                }
            } catch (Exception e) {
                System.out.println("Pages cited formatting error!");
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

        if (!author.get().equals("")) {
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
        if (!Volume.get().equals("")) {
            finalOutput += Volume.get() + ", ";
        }
        if (!Edition.get().equals("")) {
            finalOutput += Edition.get() + ", ";
        }
        if (!Issue.get().equals("")) {
            finalOutput += "(" + Issue.get() + ")" + ", ";
        }
        if (!Database.get().equals("")) {
            finalOutput += Database.get() + ". ";
        }
        if (!DatabaseService.get().equals("")) {
            finalOutput += DatabaseService.get() + ". ";
        }
        if (!Version.get().equals("")) {
            finalOutput += "ver." + Version.get() + ", ";
        }
        if (!Medium.get().equals("")) {
            finalOutput += Medium.get() + ", ";
        }
        if (!URL.get().equals("")) {
            finalOutput += URL.get() + ", ";
        }
        if (!PagesCitedStart.get().equals("") && !PagesCitedEnd.get().equals("")) {
            try {
                if (Integer.parseInt(PagesCitedEnd.get()) > Integer.parseInt(PagesCitedStart.get())) {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + PagesCitedEnd.get() + ", ";
                } else {
                    finalOutput += "pp " + PagesCitedStart.get() + "-" + "end" + ", ";
                }
            } catch (Exception e) {
                System.out.println("Pages cited formatting error!");
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

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
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

    public String getVolume() {
        return Volume.get();
    }

    public StringProperty volumeProperty() {
        return Volume;
    }

    public void setVolume(String volume) {
        this.Volume.set(volume);
    }

    public String getEdition() {
        return Edition.get();
    }

    public StringProperty editionProperty() {
        return Edition;
    }

    public void setEdition(String edition) {
        this.Edition.set(edition);
    }

    public String getIssue() {
        return Issue.get();
    }

    public StringProperty issueProperty() {
        return Issue;
    }

    public void setIssue(String issue) {
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

    public String getYearPublished() {
        return YearPublished.get();
    }

    public StringProperty yearPublishedProperty() {
        return YearPublished;
    }

    public void setYearPublished(String yearPublished) {
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

    public String getVersion() {
        return Version.get();
    }

    public StringProperty versionProperty() {
        return Version;
    }

    public void setVersion(String version) {
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

    public String getPagesCitedStart() {
        return PagesCitedStart.get();
    }

    public StringProperty pagesCitedStartProperty() {
        return PagesCitedStart;
    }

    public void setPagesCitedStart(String pagesCitedStart) {
        this.PagesCitedStart.set(pagesCitedStart);
    }

    public String getPagesCitedEnd() {
        return PagesCitedEnd.get();
    }

    public StringProperty pagesCitedEndProperty() {
        return PagesCitedEnd;
    }

    public void setPagesCitedEnd(String pagesCitedEnd) {
        this.PagesCitedEnd.set(pagesCitedEnd);
    }

    public ObservableValue<Boolean> isSelected() {
        return Selected;
    }

    public void setSelected(boolean input){Selected.setValue(input);}
}
