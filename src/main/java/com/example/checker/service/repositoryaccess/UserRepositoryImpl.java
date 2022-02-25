package com.example.checker.service.repositoryaccess;

import com.example.checker.model.User;
import com.example.checker.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryAccess {
    private UserJpaRepository repository;

    @Override
    public Optional<User> find(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<User> save(User obj) {
        if (findByUsername(obj.getUsername()).isPresent()) {
            return Optional.empty();
        }

        return Optional.of(repository.save(obj));
    }

    @Override
    public void delete(User obj) {
        repository.delete(obj);
    }

}
