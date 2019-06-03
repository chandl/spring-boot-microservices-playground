package com.chandl.microservices.currencyexchangeservice.controller;

import com.chandl.microservices.currencyexchangeservice.model.ExchangeRepository;
import com.chandl.microservices.currencyexchangeservice.model.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private ExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
//        ExchangeValue ev = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));

        ExchangeValue ev = repository.findByFromAndTo(from, to);

        log.info("{}", ev);
        ev.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return ev;
    }
}
