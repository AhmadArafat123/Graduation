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

import java.util.*;

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
        Double knn = 0.0;
        float longtid = search.getLongtid();
        float lati = search.getLati();
        String type=search.getType().toLowerCase();
        List<ServiceModel> serviceModelList;

        System.out.println(search.getAvailable() + "-------");
        if (price == 0) {

        }

        List<Provider> providers = providerRepository.findAllByAvailability(search.getAvailable());
        ArrayList<ServiceModel> serviceModels = new ArrayList<ServiceModel>();
        for (Provider p : providers) {
            Optional<List<ServiceModel>> optionalServiceModelList=serviceRepository.findByProvider(p);
            if(optionalServiceModelList.isPresent()) {
                serviceModelList=optionalServiceModelList.get();
                for (ServiceModel serviceModel:serviceModelList)
                if (serviceModel.getServiceName().toLowerCase().contains(type)) {
                    serviceModels.add(serviceModel);
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<ServiceModel> serviceModelsAfterFiltering = new ArrayList<ServiceModel>();
        if (price == 0) {
            for (ServiceModel serviceModel : serviceModels) {
                serviceQuality = Double.parseDouble(serviceModel.getQuality());
                serviceModelsAfterFiltering.add(serviceModel);
                    knn = Math.sqrt(
                            (Math.pow(serviceModel.getLongtid() - longtid, 2)) +
                            (Math.pow(serviceModel.getLati() - lati, 2))+
                                    (Math.pow(serviceQuality - quality, 2)
                    ));
                    serviceModel.setKnn(knn);
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        else {
            for (ServiceModel serviceModel : serviceModels) {
                servicePrice = serviceModel.getPrice();
                serviceQuality = Double.parseDouble(serviceModel.getQuality());
                    serviceModelsAfterFiltering.add(serviceModel);
                    knn = Math.sqrt(
                            (Math.pow(serviceModel.getLongtid() - longtid, 2)) +
                            (Math.pow(serviceModel.getLati() - lati, 2))+
                            (Math.pow(serviceQuality - quality, 2)+
                            (Math.pow(servicePrice - price, 2))
                                    ));
                serviceModel.setKnn(knn);
                }
            }
//       Collections.sort(serviceModelsAfterFiltering, new Comparator<ServiceModel>(){
//            public int compare(ServiceModel s1, ServiceModel s2)
//            {
//                return s1.getKnn().compareTo(s2.getKnn());
//            }
//        });
        Collections.sort(serviceModelsAfterFiltering , (a1, a2) -> a1.getKnn().compareTo(a2.getKnn()));


        return serviceModelsAfterFiltering;
    }

    public void deleteService(ServiceModel serviceModel) {
        serviceRepository.delete(serviceModel);
    }

    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }
}


