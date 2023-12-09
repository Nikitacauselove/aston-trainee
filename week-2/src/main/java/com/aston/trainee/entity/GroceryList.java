package com.aston.trainee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class GroceryList {
    private Long id;
    private Author author;
    private List<GroceryItem> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroceryList that = (GroceryList) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(author, that.author)) return false;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
