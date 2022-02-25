package com.example.checker.controller;

import com.example.checker.service.controller.ControllerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {
    private ControllerService service;

    @PostMapping(params = {"username", "symbol"})
    public HttpStatus notify(@RequestParam String username,
                             @RequestParam String symbol) {
        if (!service.isUserExistByUsername(username) && service.isCryptocurrencyExistBySymbol(symbol)) {
            service.addUserForNotification(username, symbol);
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
    }


}
