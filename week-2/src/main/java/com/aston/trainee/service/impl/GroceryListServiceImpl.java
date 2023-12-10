package com.aston.trainee.service.impl;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.mapper.GroceryListMapper;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.GroceryListRepository;
import com.aston.trainee.repository.impl.AuthorRepositoryImpl;
import com.aston.trainee.repository.impl.GroceryItemRepositoryImpl;
import com.aston.trainee.repository.impl.GroceryListRepositoryImpl;
import com.aston.trainee.service.GroceryListService;

import java.util.List;

public class GroceryListServiceImpl implements GroceryListService {
    private final AuthorRepository authorRepository;
    private final GroceryItemRepository groceryItemRepository;
    private final GroceryListRepository groceryListRepository;

    public GroceryListServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
        this.groceryItemRepository = new GroceryItemRepositoryImpl();
        this.groceryListRepository = new GroceryListRepositoryImpl();
    }

    public GroceryListServiceImpl(AuthorRepository authorRepository, GroceryItemRepository groceryItemRepository, GroceryListRepository groceryListRepository) {
        this.authorRepository = authorRepository;
        this.groceryItemRepository = groceryItemRepository;
        this.groceryListRepository = groceryListRepository;
    }

    public GroceryListDto create(GroceryListDto groceryListDto) {
        Author author = authorRepository.readById(groceryListDto.getAuthorId());
        List<GroceryItem> items = groceryListDto.getItems().stream().map(groceryItemRepository::readByName).toList();
        GroceryList groceryList = GroceryListMapper.fromGroceryListDto(null, author, items);

        return GroceryListMapper.toGroceryListDto(groceryListRepository.create(groceryList));
    }

    public List<GroceryListDto> read() {
        return GroceryListMapper.toGroceryListDto(groceryListRepository.read());
    }

    public GroceryListDto update(Long id, GroceryListDto groceryListDto) {
        Author author = authorRepository.readById(groceryListDto.getAuthorId());
        List<GroceryItem> items = groceryListDto.getItems().stream().map(groceryItemRepository::readByName).toList();
        GroceryList updatedGroceryList = GroceryListMapper.fromGroceryListDto(id, author, items);

        return GroceryListMapper.toGroceryListDto(groceryListRepository.update(updatedGroceryList));
    }

    public void delete(Long id) {
        groceryListRepository.delete(id);
    }
}
