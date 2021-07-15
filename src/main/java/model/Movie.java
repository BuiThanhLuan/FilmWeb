package model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Movie {
    private String _id;
    private String title;
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private int year;
    private List<String> cast;
    private String plog;
    private String fullPlot;
    private Date lastUpdated;
    private String type;
    private String poster;
    private List<String> directors;
    private List<String> writers;
    private List<String> countries;
    private List<String> genres;
}
