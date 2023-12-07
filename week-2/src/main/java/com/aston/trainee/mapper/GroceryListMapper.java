package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroceryListMapper {
    public GroceryList toGroceryList(Long id, Author author, List<GroceryItem> items) {
        GroceryList groceryList = new GroceryList();

        groceryList.setId(id);
        groceryList.setAuthor(author);
        groceryList.setItems(items);
        return groceryList;
    }

    public GroceryListDto toGroceryListDto(GroceryList groceryList) {
        GroceryListDto groceryListDto = new GroceryListDto();
        List<String> items = groceryList.getItems()
                .stream()
                .map(GroceryItem::getName)
                .toList();

        groceryListDto.setAuthorId(groceryList.getAuthor().getId());
        groceryListDto.setItems(items);
        return groceryListDto;
    }

    public List<GroceryListDto> toGroceryListDto(List<GroceryList> groceryLists) {
        return groceryLists
                .stream()
                .map(GroceryListMapper::toGroceryListDto)
                .toList();
    }
}
