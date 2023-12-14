package com.aston.trainee.mapper;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;

import java.util.List;

public class AuthorMapper {
    public static Author fromAuthorDto(Long id, AuthorDto authorDto) {
        return Author.builder()
                .id(id)
                .name(authorDto.getName())
                .build();
    }

    public static AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder()
                .name(author.getName())
                .build();
    }

    public static List<AuthorDto> toAuthorDto(List<Author> authors) {
        return authors
                .stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
    }
}
