package com.base;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    void createTable();

    Optional<T> findById(Long id);
    Optional<T> updateById(Long id, T obj);
    void deleteById(Long id);
    Optional<T> save(T obj);
    List<T> findAll();

}
