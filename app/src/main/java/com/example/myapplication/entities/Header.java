package com.example.myapplication.entities;

public class Header {
    private String title;
    private String displayTitle;

    public Header(String title) {
        this.title = title;

        this.displayTitle = title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    public String getTitle() {
        return title;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }
}
