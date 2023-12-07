package com.aston.trainee.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AuthorDto extends AuthorShortDto {
    private Long id;
}
