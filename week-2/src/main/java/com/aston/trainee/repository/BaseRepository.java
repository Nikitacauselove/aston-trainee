package com.aston.trainee.repository;

import java.util.List;

public interface BaseRepository<T> {
    T create(T entity);

    List<T> read();

    T update(Long id, T entity);

    void delete(Long id);
}
