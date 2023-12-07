package com.aston.trainee.service;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.dto.AuthorShortDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.mapper.AuthorMapper;
import com.aston.trainee.repository.AuthorRepository;

import java.util.List;

public class AuthorService implements BaseService<AuthorShortDto, AuthorDto> {
    private final AuthorRepository authorRepository = new AuthorRepository();

    public AuthorDto create(AuthorShortDto authorShortDto) {
        Author author = AuthorMapper.toAuthor(authorShortDto);

        return AuthorMapper.toAuthorDto(authorRepository.create(author));
    }

    public List<AuthorDto> read() {
        return AuthorMapper.toAuthorDto(authorRepository.read());
    }

    public AuthorDto update(Long id, AuthorShortDto authorShortDto) {
        Author updatedAuthor = AuthorMapper.toAuthor(authorShortDto);

        return AuthorMapper.toAuthorDto(authorRepository.update(id, updatedAuthor));
    }

    public void delete(Long id) {
        authorRepository.delete(id);
    }
}
