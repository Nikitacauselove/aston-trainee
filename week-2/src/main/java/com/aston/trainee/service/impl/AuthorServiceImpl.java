package com.aston.trainee.service.impl;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.mapper.AuthorMapper;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.repository.impl.AuthorRepositoryImpl;
import com.aston.trainee.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl() {
        this.authorRepository = new AuthorRepositoryImpl();
    }

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto create(AuthorDto authorDto) {
        Author author = AuthorMapper.fromAuthorDto(null, authorDto);

        return AuthorMapper.toAuthorDto(authorRepository.create(author));
    }

    public List<AuthorDto> read() {
        return AuthorMapper.toAuthorDto(authorRepository.read());
    }

    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author updatedAuthor = AuthorMapper.fromAuthorDto(id, authorDto);

        return AuthorMapper.toAuthorDto(authorRepository.update(updatedAuthor));
    }

    public void delete(Long id) {authorRepository.delete(id);
    }
}
