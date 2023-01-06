package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.repository.ProviderRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@EnableTransactionManagement
@EnableRetry

@Transactional(isolation = Isolation.SERIALIZABLE)
public class providerService {

        private final ProviderRepository providerRepository;

    public providerService(ProviderRepository serviceProviderrRepository) {
        this.providerRepository = serviceProviderrRepository;
    }

    public Optional<Provider> getProvider(long id){
        return providerRepository.findById(id);
    }

    public void updateServiceProvider(long id) {
        Optional<Provider> user = providerRepository.findById(id);
        if(user.isPresent()){
            Provider u = user.get();
            u.setCity("Nablus");

        }

    }

        public Provider createProvider(Provider provider) {
        providerRepository.save(provider);
        return provider;
    }

    public String deleteProvider(long id) {
        Optional<Provider> provider=providerRepository.findById(id);
        if (provider.isPresent()){
            providerRepository.delete(provider.get());
            return "Provider deleted";
        }
        return "Provider not found";
    }
}


