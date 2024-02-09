package com.nexgencarrental.nexGenCarRental.services.abstracts;

import java.util.List;

public interface BaseService<T, R, G, L, A, U> {

    void delete(int id);

    G getById(int id);

    List<L> getAll();

    void add(A request, Class<T> entityClass);

    void update(U updateRequest, Class<T> entityClass);
}
