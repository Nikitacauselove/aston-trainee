package com.aston.trainee.service;

import com.aston.trainee.entity.Author;
import com.aston.trainee.repository.AuthorRepository;
import com.aston.trainee.util.Expected;
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

    @BeforeEach
    void beforeEach() {
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void create() {
        when(authorRepository.create(any(Author.class))).thenReturn(Expected.AUTHOR);

        assertEquals(Expected.AUTHOR_DTO, authorService.create(Expected.AUTHOR_DTO));
    }

    @Test
    void read() {
        when(authorRepository.read()).thenReturn(List.of(Expected.AUTHOR));

        assertEquals(List.of(Expected.AUTHOR_DTO), authorService.read());
    }

    @Test
    void update() {
        when(authorRepository.update(any(Author.class))).thenReturn(Expected.AUTHOR);

        assertEquals(Expected.AUTHOR_DTO, authorService.update(1L, Expected.AUTHOR_DTO));
    }

    @Test
    void delete() {
        authorRepository.delete(1L);

        verify(authorRepository).delete(anyLong());
    }
}
