package com.aston.trainee.service;

import com.aston.trainee.dto.GroceryListDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.GroceryListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroceryListServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GroceryItemRepository groceryItemRepository;
    @Mock
    private GroceryListRepository groceryListRepository;

    private GroceryListService groceryListService;
    private final Author author = new Author(1L, "Callan");
    private final GroceryItem groceryItem = new GroceryItem(1L, "Apples");
    private final GroceryList groceryList = new GroceryList(1L, author, List.of(groceryItem));

    private final GroceryListDto groceryListDto = new GroceryListDto(1L, List.of("Apples"));

    @BeforeEach
    void beforeEach() {
        this.groceryListService = new GroceryListService(authorRepository, groceryItemRepository, groceryListRepository);
    }

    @Test
    void create() {
        when(authorRepository.readById(anyLong())).thenReturn(author);
        when(groceryItemRepository.readByName(anyString())).thenReturn(groceryItem);
        when(groceryListRepository.create(any(GroceryList.class))).thenReturn(groceryList);

        assertEquals(groceryListDto, groceryListService.create(groceryListDto));
    }

    @Test
    void read() {
        when(groceryListRepository.read()).thenReturn(List.of(groceryList));

        assertEquals(List.of(groceryListDto), groceryListService.read());
    }

    @Test
    void update() {
        when(authorRepository.readById(anyLong())).thenReturn(author);
        when(groceryItemRepository.readByName(anyString())).thenReturn(groceryItem);
        when(groceryListRepository.update(any(GroceryList.class))).thenReturn(groceryList);

        assertEquals(groceryListDto, groceryListService.update(1L, groceryListDto));
    }

    @Test
    void delete() {
        groceryListService.delete(1L);

        verify(groceryListRepository).delete(anyLong());
    }
}
