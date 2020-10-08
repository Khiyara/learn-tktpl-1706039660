package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.model;

public class Content {
    private String id;
    private String title;
    private String time;
    private String date;

    public Content(String id, String title, String time, String date) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId();
    }
}
