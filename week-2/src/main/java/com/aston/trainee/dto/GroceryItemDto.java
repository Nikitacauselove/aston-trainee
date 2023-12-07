package com.aston.trainee.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GroceryItemDto extends GroceryItemShortDto {
    private Long id;
}
