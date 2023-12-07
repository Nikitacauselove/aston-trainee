package com.aston.trainee.service;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.mapper.GroceryListMapper;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.GroceryListRepository;

import java.util.List;

public class GroceryListService implements BaseService<GroceryListDto> {
    private final AuthorRepository authorRepository = new AuthorRepository();
    private final GroceryItemRepository groceryItemRepository = new GroceryItemRepository();
    private final GroceryListRepository groceryListRepository = new GroceryListRepository();

    public GroceryListDto create(GroceryListDto groceryListDto) {
        Author author = authorRepository.readById(groceryListDto.getAuthorId());
        List<GroceryItem> items = groceryListDto.getItems().stream().map(groceryItemRepository::readByName).toList();
        GroceryList groceryList = GroceryListMapper.toGroceryList(null, author, items);

        return GroceryListMapper.toGroceryListDto(groceryListRepository.create(groceryList));
    }

    public List<GroceryListDto> read() {
        return GroceryListMapper.toGroceryListDto(groceryListRepository.read());
    }

    public GroceryListDto update(Long id, GroceryListDto groceryListDto) {
        Author author = authorRepository.readById(groceryListDto.getAuthorId());
        List<GroceryItem> items = groceryListDto.getItems().stream().map(groceryItemRepository::readByName).toList();
        GroceryList updatedGroceryList = GroceryListMapper.toGroceryList(id, author, items);

        return GroceryListMapper.toGroceryListDto(groceryListRepository.update(updatedGroceryList));
    }

    public void delete(Long id) {
        groceryListRepository.delete(id);
    }
}
