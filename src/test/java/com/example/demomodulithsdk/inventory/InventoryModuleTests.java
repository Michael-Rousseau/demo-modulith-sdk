package com.example.demomodulithsdk.inventory;

import com.example.demomodulithsdk.catalog.CoffeeCreated;
import com.example.demomodulithsdk.inventory.internal.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class InventoryModuleTests {

    @Autowired
    StockRepository stockRepository;

    @Test
    void createsStockOnCoffeeCreated(Scenario scenario) {
        scenario.publish(new CoffeeCreated(1L, "Espresso"))
                .andWaitForStateChange(() -> stockRepository.findAll())
                .andVerify(stocks -> assertThat(stocks).hasSize(1));
    }
}
