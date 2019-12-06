package com.example.myapplication.wrappers;

import com.example.myapplication.entities.Genre;

public class CheckableGenre {
    private Genre genre;

    private boolean isChecked;

    public CheckableGenre(Genre genre) {
        this.genre = genre;
    }

    public CheckableGenre(String genre) {
        this.genre = new Genre(genre);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Genre getItem() {
        return genre;
    }
}
