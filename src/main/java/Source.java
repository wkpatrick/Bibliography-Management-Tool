import java.util.List;

public class Source {

    String title;
    List<String> author;
    String MagazineTitle;
    String WebsiteTitle;
    int Volume;
    int Edition;
    int Issue;
    String Publisher;
    int YearPublished;
    String DatePublished;
    String URL;
    int Version;
    String Annotation;
    String Database;
    String DatabaseService;
    String Medium;
    int PagesCitedStart;
    int PagesCitedEnd;

    public Source(String sourceTitle) {
        title = sourceTitle;
    }

    public String ToMLA()
    {
        String finalOutput = "";
        if(title != ""){finalOutput += title + ',';}
        if(author != null){
            String authorString = author.toString();
            authorString.replace("[","");
            authorString.replace("]","");
            finalOutput += authorString + ',';
        }
        if(MagazineTitle != ""){finalOutput += MagazineTitle + ',';}
        if(WebsiteTitle != ""){finalOutput += WebsiteTitle + ',';}
        if(Volume != 0){finalOutput += Volume + ',';}
        if(Edition != 0){finalOutput += Edition + ',';}
        if(Issue != 0){finalOutput += Issue + ',';}
        if(Publisher != ""){finalOutput += Publisher + ',';}
        if(YearPublished != 0){finalOutput += YearPublished + ',';}
        if(DatePublished != ""){finalOutput += DatePublished + ',';}
        if(URL != ""){finalOutput += URL + ',';}
        if(Version != 0){finalOutput += Version + ',';}
        if(Database != ""){finalOutput += Database + ',';}
        if(DatabaseService != ""){finalOutput += DatabaseService + ',';}
        if(Medium != ""){finalOutput += Medium + ',';}
        if(PagesCitedStart != 0 && PagesCitedEnd != 0){
            if(PagesCitedEnd > PagesCitedStart){
                finalOutput += PagesCitedStart + " - " + PagesCitedEnd + ',';
            }
            else {
                finalOutput += PagesCitedStart + " - " + "end" + ',';
            }
        }
        if(Annotation != ""){finalOutput += Annotation + ',';}
        finalOutput = finalOutput.substring(0, finalOutput.length()-1);
        return finalOutput;
    }

    public void AddAuthor(String newAuthor)
    {
        author.add(newAuthor);
    }

    public void RemoveAuthor(String ditchedAuthor)
    {
        author.remove(ditchedAuthor);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }

    public int getEdition() {
        return Edition;
    }

    public void setEdition(int edition) {
        Edition = edition;
    }

    public int getIssue() {
        return Issue;
    }

    public void setIssue(int issue) {
        Issue = issue;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public int getYearPublished() {
        return YearPublished;
    }

    public void setYearPublished(int yearPublished) {
        YearPublished = yearPublished;
    }

    public String getDatePublished() {
        return DatePublished;
    }

    public void setDatePublished(String datePublished) {
        DatePublished = datePublished;
    }

    public String getWebsiteTitle() {
        return WebsiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        WebsiteTitle = websiteTitle;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public String getAnnotation() {
        return Annotation;
    }

    public void setAnnotation(String annotation) {
        Annotation = annotation;
    }

    public String getDatabase() {
        return Database;
    }

    public void setDatabase(String database) {
        Database = database;
    }

    public String getDatabaseService() {
        return DatabaseService;
    }

    public void setDatabaseService(String databaseService) {
        DatabaseService = databaseService;
    }

    public String getMedium() {
        return Medium;
    }

    public void setMedium(String medium) {
        Medium = medium;
    }

    public int getPagesCitedStart() {
        return PagesCitedStart;
    }

    public void setPagesCitedStart(int pagesCitedStart) {
        PagesCitedStart = pagesCitedStart;
    }

    public int getPagesCitedEnd() {
        return PagesCitedEnd;
    }

    public void setPagesCitedEnd(int pagesCitedEnd) {
        PagesCitedEnd = pagesCitedEnd;
    }

    public String getMagazineTitle() {
        return MagazineTitle;
    }

    public void setMagazineTitle(String magazineTitle) {
        MagazineTitle = magazineTitle;
    }
}
