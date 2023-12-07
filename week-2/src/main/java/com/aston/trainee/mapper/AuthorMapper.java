package com.aston.trainee.mapper;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.dto.AuthorShortDto;
import com.aston.trainee.entity.Author;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AuthorMapper {
    public Author toAuthor(AuthorShortDto authorShortDto) {
        Author author = new Author();

        author.setName(authorShortDto.getName());
        author.setSurname(authorShortDto.getSurname());
        return author;
    }

    public AuthorDto toAuthorDto(Author author) {
        AuthorDto.AuthorDtoBuilder<?, ?> authorDto = AuthorDto.builder();

        authorDto.id(author.getId());
        authorDto.name(author.getName());
        authorDto.surname(author.getSurname());
        return authorDto.build();
    }

    public List<AuthorDto> toAuthorDto(List<Author> authors) {
        return authors
                .stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
    }
}
