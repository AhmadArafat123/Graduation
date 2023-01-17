package com.test.testpro.service;

import com.test.testpro.model.Poke;
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
public class PokeService {

        private final ServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final PokeRepository pokeRepository;

    public PokeService(ServiceRepository serviceRepository, CustomerRepository customerRepository, ProviderRepository providerRepository, PokeRepository pokeRepository) {
        this.serviceRepository = serviceRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;

        this.pokeRepository = pokeRepository;
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

//    public ServiceModel createService(ServiceModel service,String phoneNum) {
//    Optional<Customer> cu= customerRepository.findCustomerByPhoneNum(phoneNum);
//    Optional<Provider> pr= providerRepository.findProviderByPhoneNum(phoneNum);
//    if(cu.isPresent()){
//            Customer c1=cu.get();
//            System.out.println(phoneNum + " :  exits ");
//            c1.getServiceModels().add(service);
//            service.setCustomer(c1);
//            System.out.println("hhhh");
//    }
//    else if (pr.isPresent()){
//        Provider p1=pr.get();
//        System.out.println(phoneNum + " :  exits ");
//        p1.getServiceModels().add(service);
////        service.setProvider(p1);
//    }
//    else System.out.println(phoneNum + " : doesnt exits ");
//    serviceRepository.save(service);
//    return service;
//    }

    public String deleteService(long id) {
        Optional<ServiceModel> service=serviceRepository.findById(id);
        if (service.isPresent()){
            serviceRepository.delete(service.get());
            return "Service deleted";
        }
        return "Service not found";
    }

    public List<Poke> getAllPoks(String userName) {
        return pokeRepository.findAllByServiceUserName(userName);
    }
    public List<Poke> getAllPoksForCustomer(String userName) {
        return pokeRepository.findAllByNameOfCustomer(userName);
    }


    public Poke tryTopokeService(Poke poke, long idOfService) {
        poke.setIdOfServic(idOfService);
        poke.setStatus("pending");
        return pokeRepository.save(poke);
    }

    public Poke pokeService(long id ) {
        Optional<Poke> p=pokeRepository.findById(id);
        Poke poke=p.get();
        poke.setStatus("accepted");
        return pokeRepository.save(poke);
    }

    public Poke rejectService(long id ) {
        Optional<Poke> p=pokeRepository.findById(id);
        Poke poke=p.get();
        poke.setStatus("rejected");
        return pokeRepository.save(poke);
    }

    public Poke completeService(long id ) {
        Optional<Poke> p=pokeRepository.findById(id);
        Poke poke=p.get();
        poke.setStatus("completed");
        return pokeRepository.save(poke);
    }

}


