package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;

import java.util.List;

public class GroceryItemMapper {
    public static GroceryItem fromGroceryItemDto(Long id, GroceryItemDto groceryItemDto) {
        return GroceryItem.builder()
                .id(id)
                .name(groceryItemDto.getName())
                .build();
    }

    public static GroceryItemDto toGroceryItemDto(GroceryItem groceryItem) {
        return GroceryItemDto.builder()
                .name(groceryItem.getName())
                .build();
    }

    public static List<GroceryItemDto> toGroceryItemDto(List<GroceryItem> groceryItems) {
        return groceryItems
                .stream()
                .map(GroceryItemMapper::toGroceryItemDto)
                .toList();
    }
}
