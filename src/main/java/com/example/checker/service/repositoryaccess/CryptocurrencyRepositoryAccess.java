package com.example.checker.service.repositoryaccess;

import com.example.checker.model.Cryptocurrency;

import java.util.Optional;

public interface CryptocurrencyRepositoryAccess extends RepositoryAccess<Cryptocurrency> {
    Optional<Cryptocurrency> findBySymbol(String symbol);

}
