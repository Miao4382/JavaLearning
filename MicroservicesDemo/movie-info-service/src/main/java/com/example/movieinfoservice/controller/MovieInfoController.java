package com.example.movieinfoservice.controller;

import com.example.movieinfoservice.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @GetMapping(value = "/{movieId}")
    public ResponseEntity<Movie> getMovieInfo(@PathVariable("movieId") String movieId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new Movie(movieId, "Movie title for " + movieId, "test description"));
    }
}
