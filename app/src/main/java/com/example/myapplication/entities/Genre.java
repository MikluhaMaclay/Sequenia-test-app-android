package com.example.myapplication.entities;

import java.util.Objects;

public class Genre {
    private String name;
    private String displayName;

    public Genre(String name) {
        this.name = name;
        this.displayName = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof String) {
            String s = (String) o;

            return s.equals((getName()));
        }
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;
        return Objects.equals(getName(), genre.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
