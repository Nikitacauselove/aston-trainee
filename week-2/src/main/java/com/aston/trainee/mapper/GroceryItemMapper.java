package com.aston.trainee.mapper;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.dto.GroceryItemShortDto;
import com.aston.trainee.entity.GroceryItem;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroceryItemMapper {
    public GroceryItem toGroceryItem(GroceryItemShortDto groceryItemShortDto) {
        GroceryItem groceryItem = new GroceryItem();

        groceryItem.setName(groceryItemShortDto.getName());
        return groceryItem;
    }

    public GroceryItemDto toGroceryItemDto(GroceryItem groceryItem) {
        GroceryItemDto.GroceryItemDtoBuilder<?, ?> itemDto = GroceryItemDto.builder();

        itemDto.id(groceryItem.getId());
        itemDto.name(groceryItem.getName());
        return itemDto.build();
    }

    public List<GroceryItemDto> toGroceryItemDto(List<GroceryItem> groceryItems) {
        return groceryItems
                .stream()
                .map(GroceryItemMapper::toGroceryItemDto)
                .toList();
    }
}
