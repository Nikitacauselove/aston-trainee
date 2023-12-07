package com.aston.trainee.service;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.dto.GroceryItemShortDto;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.mapper.GroceryItemMapper;
import com.aston.trainee.repository.GroceryItemRepository;

import java.util.List;

public class GroceryItemService implements BaseService<GroceryItemShortDto, GroceryItemDto> {
    private final GroceryItemRepository groceryItemRepository = new GroceryItemRepository();

    public GroceryItemDto create(GroceryItemShortDto groceryItemShortDto) {
        GroceryItem groceryItem = GroceryItemMapper.toGroceryItem(groceryItemShortDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.create(groceryItem));
    }

    public List<GroceryItemDto> read() {
        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.read());
    }

    public GroceryItemDto update(Long id, GroceryItemShortDto groceryItemShortDto) {
        GroceryItem updatedGroceryItem = GroceryItemMapper.toGroceryItem(groceryItemShortDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.update(id, updatedGroceryItem));
    }

    public void delete(Long id) {
        groceryItemRepository.delete(id);
    }
}
