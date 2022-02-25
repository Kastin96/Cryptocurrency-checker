package com.example.checker.service.cryptocurrency;

import com.example.checker.model.Cryptocurrency;
import com.example.checker.model.User;
import com.example.checker.service.repositoryaccess.CryptocurrencyRepositoryAccess;
import com.example.checker.service.repositoryaccess.UserRepositoryAccess;
import com.example.checker.service.utils.CompareUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserQuoteLogger {
    private UserRepositoryAccess userRepository;
    private CryptocurrencyRepositoryAccess cryptocurrencyRepository;

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    public void logPriceChanges() {
        for (User user : userRepository.findAll()) {
            Optional<Cryptocurrency> cryptocurrency = cryptocurrencyRepository.findBySymbol(user.getCryptocurrency().getSymbol());
            if (cryptocurrency.isPresent()) {
                double currentPrice = cryptocurrency.get().getPriceUsd();
                double oldPrice = user.getOldPrice();
                if (!CompareUtils.doublesAreEqual(currentPrice, oldPrice, oldPrice / 100)) {
                    log.warn(String.format("symbol: %s, username: %s, price change: %s%%",
                            cryptocurrency.get().getSymbol(), user.getUsername(), getPriceChange(currentPrice, oldPrice)));
                }
            }
        }
    }

    private double getPriceChange(double currentPrice, double oldPrice) {
        return (currentPrice - oldPrice) / oldPrice * 100;
    }
}
