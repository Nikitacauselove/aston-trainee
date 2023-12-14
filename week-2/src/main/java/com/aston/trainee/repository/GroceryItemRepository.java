package com.aston.trainee.repository;

import com.aston.trainee.entity.GroceryItem;

import java.util.List;

public interface GroceryItemRepository extends BaseRepository<GroceryItem> {
    GroceryItem readByName(String name);

    List<GroceryItem> readByListId(Long listId);
}
