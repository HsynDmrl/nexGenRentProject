package com.nexgencarrental.nexGenCarRental.services.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, R, G, L, A, U> {

    void delete(int id);

    G getById(int id);

    List<L> getAll();

    void add(A request, Class<T> entityClass);

    void update(U updateRequest, Class<T> entityClass);
}
