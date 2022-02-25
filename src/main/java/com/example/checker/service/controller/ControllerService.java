package com.example.checker.service.controller;

import com.example.checker.model.Cryptocurrency;
import com.example.checker.model.User;
import com.example.checker.service.repositoryaccess.CryptocurrencyRepositoryAccess;
import com.example.checker.service.repositoryaccess.UserRepositoryAccess;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ControllerService {
    private UserRepositoryAccess userRepository;
    private CryptocurrencyRepositoryAccess cryptocurrencyRepository;

    public void addUserForNotification(String username, String symbol) {
        Optional<Cryptocurrency> cryptocurrency = cryptocurrencyRepository.findBySymbol(symbol);
        cryptocurrency.ifPresent(value -> userRepository.save(User.builder()
                .username(username)
                .cryptocurrency(value)
                .oldPrice(value.getPriceUsd())
                .build()));
    }

    public boolean isUserExistByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isCryptocurrencyExistBySymbol(String symbol) {
        return cryptocurrencyRepository.findBySymbol(symbol).isPresent();

    }
}
