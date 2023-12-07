package com.aston.trainee.service;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.mapper.GroceryItemMapper;
import com.aston.trainee.repository.GroceryItemRepository;

import java.util.List;

public class GroceryItemService implements BaseService<GroceryItemDto> {
    private final GroceryItemRepository groceryItemRepository = new GroceryItemRepository();

    public GroceryItemDto create(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = GroceryItemMapper.toGroceryItem(null, groceryItemDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.create(groceryItem));
    }

    public List<GroceryItemDto> read() {
        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.read());
    }

    public GroceryItemDto update(Long id, GroceryItemDto groceryItemDto) {
        GroceryItem updatedGroceryItem = GroceryItemMapper.toGroceryItem(id, groceryItemDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.update(updatedGroceryItem));
    }

    public void delete(Long id) {
        groceryItemRepository.delete(id);
    }
}
