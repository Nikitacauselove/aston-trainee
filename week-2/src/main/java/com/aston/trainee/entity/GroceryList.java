package com.aston.trainee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class GroceryList {
    private Long id;
    private Author author;
    private List<GroceryItem> items;
}
