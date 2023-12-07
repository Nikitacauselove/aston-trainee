package com.aston.trainee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class AuthorShortDto {
    private String name;
    private String surname;
}
