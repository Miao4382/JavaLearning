package com.example.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogItem {
    private String title;
    private String description;
    private String rating;

    public CatalogItem() {}

    public CatalogItem(String title, String description, String rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }
}
