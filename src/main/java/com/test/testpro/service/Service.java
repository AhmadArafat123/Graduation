package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Poke;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.repository.CustomerRepository;
import com.test.testpro.repository.PokeRepository;
import com.test.testpro.repository.ProviderRepository;
import com.test.testpro.repository.ServiceRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@EnableTransactionManagement
@EnableRetry
@Transactional(isolation = Isolation.SERIALIZABLE)
public class Service {

        private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final PokeRepository pokeRepository;

    public Service(ServiceRepository serviceRepository, CustomerRepository customerRepository, ProviderRepository providerRepository, PokeRepository pokeRepository) {
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;

        this.pokeRepository = pokeRepository;
    }
    public List<ServiceModel> getAllServicesByName(String userName){
        return serviceRepository.findAllByUserName(userName);
    }

    public void saveService(ServiceModel user) {
        serviceRepository.save(user);
    }

    public void updateService(long id) {
        Optional<ServiceModel> user = serviceRepository.findById(id);
        if(user.isPresent()){
            ServiceModel u = user.get();
        }

    }

    public ServiceModel createService(ServiceModel service,String userName) {
    Optional<Customer> cu= customerRepository.findCustomerByUserName(userName);
    Optional<Provider> pr= providerRepository.findByUserName(userName);
    if(cu.isPresent()){
            Customer c1=cu.get();
            System.out.println(userName + " :  exits ");
            c1.getServiceModels().add(service);
            service.setCustomer(c1);
    }
    else if (pr.isPresent()){
        Provider p1=pr.get();
        System.out.println(userName + " :  exits ");
        p1.getServiceModels().add(service);
        service.setQuality(p1.getQuality());
        service.setProvider(p1);
    }
    else System.out.println(userName + " : doesnt exits ");
    serviceRepository.save(service);
    return service;
    }

    public String deleteService(long id) {
        Optional<ServiceModel> service=serviceRepository.findById(id);
        if (service.isPresent()){
            serviceRepository.delete(service.get());
            return "Service deleted";
        }
        return "Service not found";
    }

    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAllByCustomer(null);
    }


    public Poke tryTopokeService(Poke poke) {
        return pokeRepository.save(poke);
    }

    public List<ServiceModel> getAllServicesForProvider() {
        return serviceRepository.findAllByProvider(null);
    }
}


