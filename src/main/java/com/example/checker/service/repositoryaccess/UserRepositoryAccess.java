package com.example.checker.service.repositoryaccess;

import com.example.checker.model.User;

import java.util.Optional;

public interface UserRepositoryAccess extends RepositoryAccess<User> {

    Optional<User> findByUsername(String username);
}
