package com.example.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class UserRating {
    private List<Rating> ratings;
    private String userId;

    public UserRating() {
    }
}
