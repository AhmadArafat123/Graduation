package com.test.testpro.bootstrap;

import com.test.testpro.model.Customer;
import com.test.testpro.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final CustomerService userService;

    public DatabaseLoader(CustomerService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        Customer Ahmad = new Customer("Ahmad","arafat.2000@live.com","Nablus","Good");
        Customer Haitham = new Customer("Haitham","haitam.2000@live.com","Nablus","Good");
        Customer Adnan = new Customer("Adnan","Adnan.2000@live.com","Nablus","Good");
        userService.save(Ahmad);

    }


}
