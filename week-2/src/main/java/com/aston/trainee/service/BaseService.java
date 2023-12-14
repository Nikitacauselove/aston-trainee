package com.aston.trainee.service;

import java.util.List;

public interface BaseService<T> {
    T create(T entityDto);

    List<T> read();

    T update(Long id, T entityDto);

    void delete(Long id);
}
