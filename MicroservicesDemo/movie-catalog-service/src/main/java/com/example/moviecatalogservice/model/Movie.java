package com.example.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {

    private String movieId;
    private String title;
    private String description;

    public Movie(String movieId, String title, String description) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
    }

    public Movie() {}
}
