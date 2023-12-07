package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroceryItemMapper {
    public GroceryItem toGroceryItem(Long id, GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = new GroceryItem();

        groceryItem.setId(id);
        groceryItem.setName(groceryItemDto.getName());
        return groceryItem;
    }

    public GroceryItemDto toGroceryItemDto(GroceryItem groceryItem) {
        GroceryItemDto groceryItemDto = new GroceryItemDto();

        groceryItemDto.setName(groceryItem.getName());
        return groceryItemDto;
    }

    public List<GroceryItemDto> toGroceryItemDto(List<GroceryItem> groceryItems) {
        return groceryItems
                .stream()
                .map(GroceryItemMapper::toGroceryItemDto)
                .toList();
    }
}
