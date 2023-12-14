package com.aston.trainee.repository;

import java.util.List;

public interface BaseRepository<T> {
    T create(T entity);

    T readById(Long id);

    List<T> read();

    T update(T entity);

    void delete(Long id);
}
