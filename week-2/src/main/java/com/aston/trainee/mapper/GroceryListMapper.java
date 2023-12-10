package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroceryListMapper {
    public GroceryList fromGroceryListDto(Long id, Author author, List<GroceryItem> items) {
        return GroceryList.builder()
                .id(id)
                .author(author)
                .items(items)
                .build();
    }

    public GroceryListDto toGroceryListDto(GroceryList groceryList) {
        List<String> items = groceryList.getItems()
                .stream()
                .map(GroceryItem::getName)
                .toList();

        return GroceryListDto.builder()
                .authorId(groceryList.getAuthor().getId())
                .items(items)
                .build();
    }

    public List<GroceryListDto> toGroceryListDto(List<GroceryList> groceryLists) {
        return groceryLists
                .stream()
                .map(GroceryListMapper::toGroceryListDto)
                .toList();
    }
}
