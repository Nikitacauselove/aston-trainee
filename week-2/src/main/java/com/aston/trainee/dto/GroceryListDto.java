package com.aston.trainee.dto;

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
public class GroceryListDto {
    private Long authorId;
    private List<String> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroceryListDto that = (GroceryListDto) o;

        if (!Objects.equals(authorId, that.authorId)) return false;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
