package com.base;

import java.util.List;

public interface BaseDAO<T> {
    void createTable();

    T findById(Long id);
    T updateById(Long id, T obj);
    void deleteById(Long id);
    T save(T obj);
    List<T> findAll();

}
