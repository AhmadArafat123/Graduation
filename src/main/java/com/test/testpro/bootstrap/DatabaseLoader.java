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
        Customer Ahmad = new Customer("Ahmad","arafat.2000@live.com","Nablus","0595264544","1234");
        Customer Haitham = new Customer("mo","haitam.2000@live.com","Nablus","Good","0597169849");
        Customer Adnan = new Customer("Adnan","Adnan.2000@live.com","Nablus","Good","0000");

        Provider Qais=new Provider("Qais","qais.2000@live.com","Nablus",true,"doctor","5","0598216324","4321","3 years experience",false);
        Provider Ali=new Provider("Ali","ali.2000@live.com","Nablus",true,"nurse","5","0595264544","1234","7 years experience",false);

        ServiceModel s1=new ServiceModel("Qais","Nurse","5 years experiance ",false,200,50,40,"4","",true,1.0);
           s1.setProvider(Qais);
        ServiceModel s2=new ServiceModel("Qais","EL Nurse","5 years experiance ",false,100,30,30,"3","",true,1.0);
        s2.setProvider(Qais);
        ServiceModel s3=new ServiceModel("Ali","EL Nurse","5 years experiance ",false,150,80,80,"2","",true,1.0);
        s3.setProvider(Ali);
        customerService.save(Ahmad);
        customerService.save(Haitham);
        customerService.save(Adnan);
        providerService.save(Qais);
        providerService.save(Ali);

        service.saveService(s1);
        service.saveService(s2);
        service.saveService(s3);

    }


}
