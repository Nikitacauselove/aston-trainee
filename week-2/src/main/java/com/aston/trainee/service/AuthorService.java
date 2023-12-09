package com.aston.trainee.service;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.mapper.AuthorMapper;
import com.aston.trainee.repository.AuthorRepository;

import java.util.List;

public class AuthorService implements BaseService<AuthorDto> {
    private final AuthorRepository authorRepository;

    public AuthorService() {
        this.authorRepository = new AuthorRepository();
    }

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto create(AuthorDto authorDto) {
        Author author = AuthorMapper.toAuthor(null, authorDto);

        return AuthorMapper.toAuthorDto(authorRepository.create(author));
    }

    public List<AuthorDto> read() {
        return AuthorMapper.toAuthorDto(authorRepository.read());
    }

    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author updatedAuthor = AuthorMapper.toAuthor(id, authorDto);

        return AuthorMapper.toAuthorDto(authorRepository.update(updatedAuthor));
    }

    public void delete(Long id) {
        authorRepository.delete(id);
    }
}
