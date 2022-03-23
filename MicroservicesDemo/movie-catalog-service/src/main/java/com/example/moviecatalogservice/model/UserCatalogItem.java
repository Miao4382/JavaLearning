package com.example.moviecatalogservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCatalogItem {
    private List<CatalogItem> catalogItems;
    private String userId;

    public UserCatalogItem() {}

    public UserCatalogItem(List<CatalogItem> catalogItems, String userId) {
        this.catalogItems = catalogItems;
        this.userId = userId;
    }
}
