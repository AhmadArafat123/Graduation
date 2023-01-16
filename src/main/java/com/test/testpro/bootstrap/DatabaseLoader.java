package com.test.testpro.bootstrap;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.CustomerService;
import com.test.testpro.service.Service;
import com.test.testpro.service.providerService;
import org.joda.time.LocalTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final CustomerService customerService;
    private final providerService providerService;
    private final Service service;


    public DatabaseLoader(CustomerService customerService, com.test.testpro.service.providerService providerService, Service service) {
        this.customerService = customerService;
        this.providerService = providerService;
        this.service = service;
    }

    @Override
    public void run(String... args) {
        Customer Ahmad = new Customer("Ahmad","arafat.2000@live.com","Nablus","Good");
        Customer Haitham = new Customer("Haitham","haitam.2000@live.com","Nablus","Good");
        Customer Adnan = new Customer("Adnan","Adnan.2000@live.com","Nablus","Good");

        Provider Qais=new Provider("Qais","qais.2000@live.com","Nablus","Good");

        ServiceModel Shoubi=new ServiceModel("Shoubi","Doctor",0,20,  "11:00","Nablus",100,"Good");

        customerService.save(Ahmad);
        customerService.save(Haitham);
        customerService.save(Adnan);
        providerService.save(Qais);

    }


}
