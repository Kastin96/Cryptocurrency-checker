package com.example.checker.service.performers;

import com.example.checker.model.Cryptocurrency;
import com.example.checker.service.repositoryaccess.CryptocurrencyRepositoryAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CryptocurrencyRefreshCycle {
    private static final String API_URI = "https://api.coinlore.net/api/ticker/?id=";
    private CryptocurrencyRepositoryAccess cryptocurrencyRepository;
    private RestTemplate restTemplate;
    private List<Cryptocurrency> currencies;

    @SneakyThrows
    @Scheduled(fixedDelay = 60000, initialDelay = 0)
    public void upgradeAvailableCryptocurrency() {
        for (Cryptocurrency currency : currencies) {

            ResponseEntity<Cryptocurrency[]> responseEntity = restTemplate.getForEntity(API_URI + currency.getId(),
                    Cryptocurrency[].class);
            cryptocurrencyRepository.save(Arrays.stream(Objects.requireNonNull(responseEntity.getBody()))
                    .findFirst().orElseThrow(RuntimeException::new));
        }
    }

    @Autowired
    public void setCurrencies(@Value("${currencies}") String currencies) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cryptocurrency[] objects = objectMapper.readValue(currencies, Cryptocurrency[].class);
        this.currencies = Arrays.asList(objects);
    }
}
