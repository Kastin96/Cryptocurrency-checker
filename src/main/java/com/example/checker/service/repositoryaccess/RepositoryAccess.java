package com.example.checker.service.repositoryaccess;

import java.util.List;
import java.util.Optional;

public interface RepositoryAccess<T> {

    Optional<T> find(long id);

    List<T> findAll();

    Optional<T> save(T obj);

    void delete(T obj);
}
