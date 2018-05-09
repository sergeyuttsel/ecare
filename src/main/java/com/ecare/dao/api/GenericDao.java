package com.ecare.dao.api;

import java.util.List;


public interface GenericDao<T> {
    T create(T entity);
    T update(T entity);
    void delete(T entity);
    T find(int id);
    
}
