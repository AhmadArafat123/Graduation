package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Provider;
import com.test.testpro.model.Rate;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.repository.ProviderRepository;
import com.test.testpro.repository.RateRepository;
import com.test.testpro.repository.ServiceRepository;
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
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final ServiceRepository serviceRepository;
    private final RateRepository rateRepository;

    public ProviderService(ProviderRepository providerRepository, ServiceRepository serviceRepository, RateRepository rateRepository) {
        this.providerRepository = providerRepository;
        this.serviceRepository = serviceRepository;
        this.rateRepository = rateRepository;
    }

    public void save(com.test.testpro.model.Provider provider) {
        providerRepository.save(provider);
    }

    public Optional<com.test.testpro.model.Provider> getProvider(long id) {
        return providerRepository.findById(id);
    }

    public boolean updateServiceProvider(long id) {
        Optional<com.test.testpro.model.Provider> provider = providerRepository.findById(id);
        if (!provider.isPresent()) {
            throw new ApiRequestException("Provider doesnt exit id is wrong");
        }
        Provider p = provider.get();
        if (p.isAvailability())
            p.setAvailability(false);
        else
            p.setAvailability(true);
        List<ServiceModel> serviceModelList = p.getServiceModels();
        boolean ave = p.isAvailability();
        for (ServiceModel serviceModel : serviceModelList) {
            serviceModel.setAvailable(ave);
        }
        return p.isAvailability();
    }

    public Provider createProvider(Provider provider) {
        String name= provider.getUserName();
        Optional<Provider> provider1=providerRepository.findByUserName(name);
        if(provider1.isPresent()){
            throw new ApiRequestException("This is a used userName Please Enter another");
        }
        provider.setAvailability(false);

        providerRepository.save(provider);
        return provider;
    }

    public String deleteProvider(long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            providerRepository.delete(provider.get());
            return "Provider deleted";
        }
        return "Provider not found";
    }

    public Optional<com.test.testpro.model.Provider> getProviderByName(String name) {
        return providerRepository.findByUserName(name);
    }

    public Optional<Provider> getProvidertoLog(String email, String password) {
        Optional<Provider> p = providerRepository.findProviderByEmail(email);
        if (!p.isPresent() || !password.equalsIgnoreCase(p.get().getPassword())) {
            throw new ApiRequestException("Error email or password is wrong");
        }
        return p;
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAllByApproved(true);
    }

    public List<Provider> getAllRequests() {
        return providerRepository.findAllByApproved(false);
    }


    public String rateProvider(Rate rate) {
        Optional<Provider> p=providerRepository.findByUserName(rate.getProviderUserName());
        if(!p.isPresent()) {
            throw new ApiRequestException("Invalide rate");
        }
            Provider p1=p.get();
            rateRepository.save(rate);
            Integer quality = Integer.parseInt(p1.getQuality());
            List<Rate> rateList = rateRepository.findAllByProviderUserName(rate.getProviderUserName());
            Integer size=1;
            for (Rate rate1 : rateList) {
                quality +=Integer.parseInt(rate1.getQuality());
                size++;
            }
            quality=quality/size;
            p1.setQuality(quality.toString());
            return quality.toString();
    }
}


