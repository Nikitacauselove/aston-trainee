package com.aston.trainee.util;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Expected {
    public final AuthorDto AUTHOR_DTO = new AuthorDto("Callan");
    public final GroceryItemDto GROCERY_ITEM_DTO = new GroceryItemDto("Apples");
    public final GroceryListDto GROCERY_LIST_DTO = new GroceryListDto(1L, List.of("Apples"));

    public final Author AUTHOR = new Author(1L, "Callan");
    public final GroceryItem GROCERY_ITEM = new GroceryItem(1L, "Apples");
    public final GroceryList GROCERY_LIST = new GroceryList(1L, AUTHOR, List.of(GROCERY_ITEM));
}
