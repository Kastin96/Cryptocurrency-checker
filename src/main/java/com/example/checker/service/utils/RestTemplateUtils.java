package com.example.checker.service.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateUtils {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
