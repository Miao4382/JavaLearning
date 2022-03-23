package com.example.movieratingsdataservice.controller;

import com.example.movieratingsdataservice.model.Rating;
import com.example.movieratingsdataservice.model.UserRating;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/ratingsdata")
public class MovieRatingDataController {
    @GetMapping(value = "/{movieId}")
    public ResponseEntity<Rating> getRating(@PathVariable("movieId") String movieId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new Rating(movieId, ThreadLocalRandom.current().nextInt(0, 11)));
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<UserRating> getUserRating(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new UserRating(userId));
    }
}
