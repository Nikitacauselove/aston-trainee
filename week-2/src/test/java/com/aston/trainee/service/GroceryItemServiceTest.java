package com.aston.trainee.service;

import com.aston.trainee.dto.GroceryItemDto;
import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.repository.GroceryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroceryItemServiceTest {
    @Mock
    private GroceryItemRepository groceryItemRepository;
    private GroceryItemService groceryItemService;

    private final GroceryItemDto groceryItemDto = new GroceryItemDto("Apples");
    private final GroceryItem groceryItem = new GroceryItem(1L, "Apples");

    @BeforeEach
    void beforeEach() {
        groceryItemService = new GroceryItemService(groceryItemRepository);
    }

    @Test
    void create() {
        when(groceryItemRepository.create(any(GroceryItem.class))).thenReturn(groceryItem);

        assertEquals(groceryItemDto, groceryItemService.create(groceryItemDto));
    }

    @Test
    void read() {
        when(groceryItemRepository.read()).thenReturn(List.of(groceryItem));

        assertEquals(List.of(groceryItemDto), groceryItemService.read());
    }

    @Test
    void update() {
        when(groceryItemRepository.update(any(GroceryItem.class))).thenReturn(groceryItem);

        assertEquals(groceryItemDto, groceryItemService.update(1L, groceryItemDto));
    }

    @Test
    void delete() {
        groceryItemService.delete(1L);

        verify(groceryItemRepository).delete(anyLong());
    }
}
