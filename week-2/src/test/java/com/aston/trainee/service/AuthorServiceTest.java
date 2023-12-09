package com.aston.trainee.service;

import com.aston.trainee.dto.AuthorDto;
import com.aston.trainee.entity.Author;
import com.aston.trainee.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    private final AuthorDto authorDto = new AuthorDto("Callan");
    private final Author author = new Author(1L, "Callan");

    @BeforeEach
    void beforeEach() {
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void create() {
        when(authorRepository.create(any(Author.class))).thenReturn(author);

        assertEquals(authorDto, authorService.create(authorDto));
    }

    @Test
    void read() {
        when(authorRepository.read()).thenReturn(List.of(author));

        assertEquals(List.of(authorDto), authorService.read());
    }

    @Test
    void update() {
        when(authorRepository.update(any(Author.class))).thenReturn(author);

        assertEquals(authorDto, authorService.update(1L, authorDto));
    }

    @Test
    void delete() {
        authorRepository.delete(1L);

        verify(authorRepository).delete(anyLong());
    }
}
