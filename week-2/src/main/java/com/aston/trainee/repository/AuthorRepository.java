package com.aston.trainee.repository;

import com.aston.trainee.entity.Author;

public interface AuthorRepository extends BaseRepository<Author> {
    Author readById(Long id);
}
