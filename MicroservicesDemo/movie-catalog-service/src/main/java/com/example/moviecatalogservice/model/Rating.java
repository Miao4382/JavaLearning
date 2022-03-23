package com.example.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private String movieId;
    private String rate;

    public Rating(String movieId, int rate) {
        this.movieId = movieId;
        this.rate = String.valueOf(rate);
    }

    public Rating() {}
}
