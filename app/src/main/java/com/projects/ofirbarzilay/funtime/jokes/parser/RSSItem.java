package com.projects.ofirbarzilay.funtime.jokes.parser;

/**
 * Created by Ofir.Barzilay on 15/12/2014.
 */
public class RSSItem {
    private String title = null;
    private String description = null;
    private String date = null;
    private String link;

    public RSSItem() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

}
