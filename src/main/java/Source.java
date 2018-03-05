public class Source {

    String title;
    String author;

    public Source(String sourceTitle, String authorName) {
        title = sourceTitle;
        author = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
