package com.aston.trainee.service;

import com.aston.trainee.entity.GroceryItem;
import com.aston.trainee.repository.GroceryItemRepository;
import com.aston.trainee.service.impl.GroceryItemServiceImpl;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroceryItemServiceTest {
    @Mock
    private GroceryItemRepository groceryItemRepository;

    private GroceryItemService groceryItemService;

    @BeforeEach
    void beforeEach() {
        groceryItemService = new GroceryItemServiceImpl(groceryItemRepository);
    }

    @Test
    void create() {
        when(groceryItemRepository.create(any(GroceryItem.class))).thenReturn(Expected.GROCERY_ITEM);

        assertEquals(Expected.GROCERY_ITEM_DTO, groceryItemService.create(Expected.GROCERY_ITEM_DTO));
    }

    @Test
    void read() {
        when(groceryItemRepository.read()).thenReturn(List.of(Expected.GROCERY_ITEM));

        assertEquals(List.of(Expected.GROCERY_ITEM_DTO), groceryItemService.read());
    }

    @Test
    void update() {
        when(groceryItemRepository.update(any(GroceryItem.class))).thenReturn(Expected.GROCERY_ITEM);

        assertEquals(Expected.GROCERY_ITEM_DTO, groceryItemService.update(1L, Expected.GROCERY_ITEM_DTO));
    }

    @Test
    void delete() {
        groceryItemService.delete(1L);

        verify(groceryItemRepository).delete(anyLong());
    }
}
