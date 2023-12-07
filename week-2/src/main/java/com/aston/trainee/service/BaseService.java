package com.aston.trainee.service;

import java.util.List;

public interface BaseService<T, R> {
    R create(T entityDto);

    List<R> read();

    R update(Long id, T entityDto);

    void delete(Long id);
}
