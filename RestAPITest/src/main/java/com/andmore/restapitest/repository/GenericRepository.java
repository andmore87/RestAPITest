package com.andmore.restapitest.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Repository
 * @author Andres.
 */
public interface GenericRepository <T> {
    public List<T> getAll(Class<T> typeClass);
    public T findByPK(Class<T> typeClass, Serializable id);
    public void saveOrUpdate(T object);
    public void delete(T object);
}
