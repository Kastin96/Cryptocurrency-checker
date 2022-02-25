package com.example.checker.controller;

import com.example.checker.exceptions.NotFoundException;
import com.example.checker.model.Cryptocurrency;
import com.example.checker.service.repositoryaccess.CryptocurrencyRepositoryAccess;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class CryptocurrencyController {
    private CryptocurrencyRepositoryAccess repository;

    @GetMapping
    public List<Cryptocurrency> getAll() {
        return repository.findAll();
    }

    @GetMapping(params = {"id"})
    public Cryptocurrency getById(@RequestParam Long id) {
        return repository.find(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping(params = {"symbol"})
    public Cryptocurrency getBySymbol(@RequestParam String symbol) {
        return repository.findBySymbol(symbol).orElseThrow(NotFoundException::new);
    }
}
