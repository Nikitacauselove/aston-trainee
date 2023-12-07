package com.aston.trainee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class GroceryListDto {
    private Long authorId;
    private List<String> items;
}
