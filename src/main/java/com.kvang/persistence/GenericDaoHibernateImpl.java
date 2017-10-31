package com.kvang.persistence;

import java.io.Serializable;

public class GenericDaoHibernateImpl <T, PK extends Serializable>
        implements GenericDao<T, PK> {
    private Class<T> type;

    @Override
    public PK create(T o) {
        return null;
    }

    @Override
    public T read(PK id) {
        return null;
    }

    @Override
    public void update(T o) {

    }

    @Override
    public void delete(T o) {

    }
}
