package com.aston.trainee.service.impl;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.mapper.GroceryItemMapper;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.impl.GroceryItemRepositoryImpl;
import com.aston.trainee.service.GroceryItemService;

import java.util.List;

public class GroceryItemServiceImpl implements GroceryItemService {
    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemServiceImpl() {
        this.groceryItemRepository = new GroceryItemRepositoryImpl();
    }

    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    public GroceryItemDto create(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = GroceryItemMapper.fromGroceryItemDto(null, groceryItemDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.create(groceryItem));
    }

    public List<GroceryItemDto> read() {
        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.read());
    }

    public GroceryItemDto update(Long id, GroceryItemDto groceryItemDto) {
        GroceryItem updatedGroceryItem = GroceryItemMapper.fromGroceryItemDto(id, groceryItemDto);

        return GroceryItemMapper.toGroceryItemDto(groceryItemRepository.update(updatedGroceryItem));
    }

    public void delete(Long id) {
        groceryItemRepository.delete(id);
    }
}
