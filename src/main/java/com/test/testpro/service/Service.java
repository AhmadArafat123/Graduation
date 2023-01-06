package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.repository.ServiceRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@org.springframework.stereotype.Service
@EnableTransactionManagement
@EnableRetry

@Transactional(isolation = Isolation.SERIALIZABLE)
public class Service {

        private final ServiceRepository serviceRepository;

    public Service(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public Optional<ServiceModel> getService(long id){
        return serviceRepository.findById(id);
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

    public ServiceModel createService(ServiceModel service) {
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
}


