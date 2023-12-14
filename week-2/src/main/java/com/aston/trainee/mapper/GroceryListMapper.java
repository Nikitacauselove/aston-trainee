package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;

import java.util.List;

public class GroceryListMapper {
    public static GroceryList fromGroceryListDto(Long id, Author author, List<GroceryItem> items) {
        return GroceryList.builder()
                .id(id)
                .author(author)
                .items(items)
                .build();
    }

    public static GroceryListDto toGroceryListDto(GroceryList groceryList) {
        List<String> items = groceryList.getItems()
                .stream()
                .map(GroceryItem::getName)
                .toList();

        return GroceryListDto.builder()
                .authorId(groceryList.getAuthor().getId())
                .items(items)
                .build();
    }

    public static List<GroceryListDto> toGroceryListDto(List<GroceryList> groceryLists) {
        return groceryLists
                .stream()
                .map(GroceryListMapper::toGroceryListDto)
                .toList();
    }
}
