package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Customer;
import com.test.testpro.model.EmailEntity;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@EnableTransactionManagement
@EnableRetry
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AdminService {

    private final CustomerService customerService;
    private final ProviderService providerService;
    private final EmailService emailService;
    private final com.test.testpro.service.Service service;


    public AdminService(CustomerService customerService, ProviderService providerService, EmailService emailService, com.test.testpro.service.Service service) {
        this.customerService = customerService;
        this.providerService = providerService;
        this.emailService = emailService;
        this.service = service;
    }

    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    public List<Provider> getProviders() {
        return providerService.getAllProviders();
    }

    public List<Provider> getRequests() {
        return providerService.getAllRequests();
    }

    public String deleteCustomer(long id) {
        return customerService.deleteUser(id);
    }

    public String deleteProvider(long id) {
        EmailEntity emailEntity = new EmailEntity(providerService.getProvider(id).get().getEmail(),
                "Thanks for your trust but we are sorry",
                "Service Provider team",null);
        return providerService.deleteProvider(id);
    }

    public String acceptProvider(long id) {
        Optional<Provider> provider = providerService.getProvider(id);
        if (!provider.isPresent()) {
            throw new ApiRequestException("no provider found");
        }
        Provider p = provider.get();
        p.setApproved(true);
        EmailEntity emailEntity = new EmailEntity(p.getEmail(),
                "Welcome to our application we are sou proud that you become one of our big family",
                "Welcome message from Service Provider Family",null);
        return emailService.sendSimpleMail(emailEntity);
    }

    public List<ServiceModel> getAllServices() {
        return service.getAllServices();
    }
}


