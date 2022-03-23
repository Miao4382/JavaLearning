package com.example.moviecatalogservice.controller;

import com.example.moviecatalogservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/catalog")
public class MovieCatalogResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Using RestTemplate to call the external API
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserCatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // get all rated movies of this user
        UserRating userRating = restTemplate.getForObject(
                "http://movie-ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        // for each movie id, invoke movie info service to get the details of the movie
        List<CatalogItem> items = userRating.getRatings().stream().map(rating -> {

            // invoke movie info service here
            Movie movie = restTemplate.getForObject(
                    "http://movie-info-service/movies/" + rating.getMovieId(),
                    Movie.class);
            // use the movie info to create a catalog item
            return new CatalogItem(movie.getTitle(), movie.getDescription(), rating.getRate());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new UserCatalogItem(items, userId));
    }

    // Using WebClient to call the external API
    @GetMapping(value = "/webclient/{userId}")
    public ResponseEntity<UserCatalogItem> getCatalog2(@PathVariable("userId") String userId) {

        // get all rated movies of this user
        UserRating userRating = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8083/ratingsdata/user/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        List<CatalogItem> items = userRating.getRatings().stream().map(rating -> {

            // Get movie info by invoking movie info service API here
            Movie movie = webClientBuilder
                    .build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(movie.getTitle(), movie.getDescription(), rating.getRate());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new UserCatalogItem(items, userId));
    }
}
