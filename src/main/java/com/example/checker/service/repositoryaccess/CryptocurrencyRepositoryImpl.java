package com.example.checker.service.repositoryaccess;

import com.example.checker.model.Cryptocurrency;
import com.example.checker.repository.jpa.CryptocurrencyJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CryptocurrencyRepositoryImpl implements CryptocurrencyRepositoryAccess {
    private CryptocurrencyJpaRepository repository;

    @Override
    public Optional<Cryptocurrency> find(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Cryptocurrency> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cryptocurrency> save(Cryptocurrency obj) {
        return Optional.of(repository.save(obj));
    }

    @Override
    public void delete(Cryptocurrency obj) {
        repository.delete(obj);
    }

    public Optional<Cryptocurrency> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }
}
