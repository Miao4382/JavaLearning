package com.example.movieratingsdataservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class UserRating {
    private List<Rating> ratings;
    private String userId;

    public UserRating() {
    }

    public UserRating(String userId) {
        this.userId = userId;

        // will generate a random list
        int n = ThreadLocalRandom.current().nextInt(3, 11);
        ratings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ratings.add(new Rating("" + (100 + i), ThreadLocalRandom.current().nextInt(0, 11)));
        }
    }
}
