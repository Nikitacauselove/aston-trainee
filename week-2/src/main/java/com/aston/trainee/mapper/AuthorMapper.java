package com.aston.trainee.mapper;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AuthorMapper {
    public Author toAuthor(Long id, AuthorDto authorDto) {
        Author author = new Author();

        author.setId(id);
        author.setName(authorDto.getName());
        return author;
    }

    public AuthorDto toAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setName(author.getName());
        return authorDto;
    }

    public List<AuthorDto> toAuthorDto(List<Author> authors) {
        return authors
                .stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
    }
}
