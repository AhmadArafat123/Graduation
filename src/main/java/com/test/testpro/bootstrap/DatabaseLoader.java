package com.test.testpro.bootstrap;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.CustomerService;
import com.test.testpro.service.Service;
import com.test.testpro.service.ProviderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final CustomerService customerService;
    private final ProviderService providerService;
    private final Service service;


    public DatabaseLoader(CustomerService customerService, ProviderService providerService, Service service) {
        this.customerService = customerService;
        this.providerService = providerService;
        this.service = service;
    }

    @Override
    public void run(String... args) {
        Customer Ahmad = new Customer("Ahmad","arafat.2000@live.com","Nablus","Good","0595264544");
        Customer Haitham = new Customer("mo","haitam.2000@live.com","Nablus","Good","0597169849");
        Customer Adnan = new Customer("Adnan","Adnan.2000@live.com","Nablus","Good","0000");
        Provider Qais=new Provider("Qais","qais.2000@live.com","Nablus",true,"doctor","5","000000","55555","dog",false);

//        ServiceModel Shoubi=new ServiceModel("Shoubi","Doctor",0,20,  "11:00","Nablus",100,"Good");

        customerService.save(Ahmad);
        customerService.save(Haitham);
        customerService.save(Adnan);
        providerService.save(Qais);
//        service.saveService(Shoubi);

    }


}
