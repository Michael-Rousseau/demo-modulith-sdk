package com.example.demomodulithsdk.catalog;

import com.example.demomodulithsdk.catalog.internal.CoffeeEntity;
import com.example.demomodulithsdk.catalog.internal.CoffeeRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Catalog {

    private final CoffeeRepository repository;
    private final ApplicationEventPublisher events;

    Catalog(CoffeeRepository repository, ApplicationEventPublisher events) {
        this.repository = repository;
        this.events = events;
    }

    @Transactional
    public Long createCoffee(String name, int price) {
        CoffeeEntity saved = repository.save(new CoffeeEntity(name, price));
        events.publishEvent(new CoffeeCreated(saved.getId(), saved.getName()));
        return saved.getId();
    }
}
