package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.model.Search;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.repository.CustomerRepository;
import com.test.testpro.repository.PokeRepository;
import com.test.testpro.repository.ProviderRepository;
import com.test.testpro.repository.ServiceRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<ServiceModel> getAllServicesByName(String userName) {
        return serviceRepository.findAllByUserName(userName);
    }

    public void saveService(ServiceModel user) {
        serviceRepository.save(user);
    }

    public void updateService(long id) {
        Optional<ServiceModel> user = serviceRepository.findById(id);
        if (user.isPresent()) {
            ServiceModel u = user.get();
        }

    }

    public ServiceModel createService(ServiceModel service, String userName) {
        Optional<Customer> cu = customerRepository.findCustomerByUserName(userName);
        Optional<Provider> pr = providerRepository.findByUserName(userName);
        if (cu.isPresent()) {
            Customer c1 = cu.get();
            System.out.println(userName + " :  exits ");
            c1.getServiceModels().add(service);
            service.setCustomer(c1);
        } else if (pr.isPresent()) {
            Provider p1 = pr.get();
            System.out.println(userName + " :  exits ");
            p1.getServiceModels().add(service);
            service.setQuality(p1.getQuality());
            service.setAvailable(p1.isAvailability());
            service.setProvider(p1);
        } else System.out.println(userName + " : doesnt exits ");
        serviceRepository.save(service);
        return service;
    }

    public String deleteService(long id) {
        Optional<ServiceModel> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            serviceRepository.delete(service.get());
            return "Service deleted";
        }
        return "Service not found";
    }

    public List<ServiceModel> getAllServicesForCustomer() {
        return serviceRepository.findAllByCustomer(null);
    }

    public List<ServiceModel> getAllServicesForProvider() {
        return serviceRepository.findAllByProvider(null);
    }

    public List<ServiceModel> find(Search search) {
        double servicePrice = 0;
        double serviceQuality = 0;
        double quality = search.getQuality();
        double price = search.getPrice();
        double distance = 0;
        float longtid = search.getLongtid();
        float lati = search.getLati();
        System.out.println(search.getAvailable() + "-------");
        if (price == 0) {

        }

        List<Provider> providers = providerRepository.findAllByAvailability(search.getAvailable());
        ArrayList<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
        for (Provider p : providers) {

            ServiceModel serviceModel = serviceRepository.findByProviderAndServiceName(p, search.getType());
            if(serviceModel!=null)
            serviceModels.add(serviceModel);
        }
        ArrayList<ServiceModel> serviceModelsAfterFiltering = new ArrayList<ServiceModel>();
        if (price == 0) {
            for (ServiceModel serviceModel : serviceModels) {
                serviceQuality = Double.parseDouble(serviceModel.getQuality());
                if (Math.abs(quality - serviceQuality) < 3) {
                    serviceModelsAfterFiltering.add(serviceModel);
                    distance = Math.sqrt((Math.pow(serviceModel.getLongtid() - longtid, 2)) + (Math.pow(serviceModel.getLati() - lati, 2)));
                }
            }
        } else {
            for (ServiceModel serviceModel : serviceModels) {
                servicePrice = serviceModel.getPrice();
                serviceQuality = Double.parseDouble(serviceModel.getQuality());
                if (Math.abs(price - servicePrice) < 25 && Math.abs(quality - serviceQuality) < 3) {
                    serviceModelsAfterFiltering.add(serviceModel);
                    distance = Math.sqrt((Math.pow(serviceModel.getLongtid() - longtid, 2)) + (Math.pow(serviceModel.getLati() - lati, 2)));
                }
            }
        }
        return serviceModelsAfterFiltering;
    }

    public void deleteService(ServiceModel serviceModel) {
        serviceRepository.delete(serviceModel);
    }

    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }
}


