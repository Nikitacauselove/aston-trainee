package com.aston.trainee.mapper;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AuthorMapper {
    public Author fromAuthorDto(Long id, AuthorDto authorDto) {
        return Author.builder()
                .id(id)
                .name(authorDto.getName())
                .build();
    }

    public AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder()
                .name(author.getName())
                .build();
    }

    public List<AuthorDto> toAuthorDto(List<Author> authors) {
        return authors
                .stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
    }
}
