package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroceryItemMapper {
    public GroceryItem fromGroceryItemDto(Long id, GroceryItemDto groceryItemDto) {
        return GroceryItem.builder()
                .id(id)
                .name(groceryItemDto.getName())
                .build();
    }

    public GroceryItemDto toGroceryItemDto(GroceryItem groceryItem) {
        return GroceryItemDto.builder()
                .name(groceryItem.getName())
                .build();
    }

    public List<GroceryItemDto> toGroceryItemDto(List<GroceryItem> groceryItems) {
        return groceryItems
                .stream()
                .map(GroceryItemMapper::toGroceryItemDto)
                .toList();
    }
}
