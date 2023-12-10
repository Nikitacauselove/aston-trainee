package com.aston.trainee.service;

import com.aston.trainee.entity.GroceryList;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.repository.GroceryListRepository;
import com.aston.trainee.service.impl.GroceryListServiceImpl;
import com.aston.trainee.util.Expected;
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

    @BeforeEach
    void beforeEach() {
        this.groceryListService = new GroceryListServiceImpl(authorRepository, groceryItemRepository, groceryListRepository);
    }

    @Test
    void create() {
        when(authorRepository.readById(anyLong())).thenReturn(Expected.AUTHOR);
        when(groceryItemRepository.readByName(anyString())).thenReturn(Expected.GROCERY_ITEM);
        when(groceryListRepository.create(any(GroceryList.class))).thenReturn(Expected.GROCERY_LIST);

        assertEquals(Expected.GROCERY_LIST_DTO, groceryListService.create(Expected.GROCERY_LIST_DTO));
    }

    @Test
    void read() {
        when(groceryListRepository.read()).thenReturn(List.of(Expected.GROCERY_LIST));

        assertEquals(List.of(Expected.GROCERY_LIST_DTO), groceryListService.read());
    }

    @Test
    void update() {
        when(authorRepository.readById(anyLong())).thenReturn(Expected.AUTHOR);
        when(groceryItemRepository.readByName(anyString())).thenReturn(Expected.GROCERY_ITEM);
        when(groceryListRepository.update(any(GroceryList.class))).thenReturn(Expected.GROCERY_LIST);

        assertEquals(Expected.GROCERY_LIST_DTO, groceryListService.update(1L, Expected.GROCERY_LIST_DTO));
    }

    @Test
    void delete() {
        groceryListService.delete(1L);

        verify(groceryListRepository).delete(anyLong());
    }
}
