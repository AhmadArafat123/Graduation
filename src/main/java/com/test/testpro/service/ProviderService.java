package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
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
public class ProviderService {
    private final ProviderRepository providerRepository;
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }
    public void save(com.test.testpro.model.Provider provider) {
        providerRepository.save(provider);
    }
    public Optional<com.test.testpro.model.Provider> getProvider(long id){
        return providerRepository.findById(id);
    }
    public void updateServiceProvider(long id) {
        Optional<com.test.testpro.model.Provider> user = providerRepository.findById(id);
        if(user.isPresent()){
            com.test.testpro.model.Provider u = user.get();
            u.setCity("Nablus");
        }
    }

        public com.test.testpro.model.Provider createProvider(com.test.testpro.model.Provider provider) {
        providerRepository.save(provider);
        return provider;
    }


    public String deleteProvider(long id) {
        Optional<com.test.testpro.model.Provider> provider=providerRepository.findById(id);
        if (provider.isPresent()){
            providerRepository.delete(provider.get());
            return "Provider deleted";
        }
        return "Provider not found";
    }

    public Optional<com.test.testpro.model.Provider> getProviderByName(String name) {
        return providerRepository.findByUserName(name);
    }

    public Optional<Provider> getProvidertoLog(String email, String password) {
        Optional<Provider> p=  providerRepository.findProviderByEmail(email);
        if(!p.isPresent() || !password.equalsIgnoreCase(p.get().getPassword())){
            throw new ApiRequestException("Error email or password is wrong");
        }
        return p;
    }
//    public List<Provider> find(Boolean av, String type, int quality, int price){
//        List<Provider> providers=providerRepository.findAllByAvailabilityAndTypeOfService(av,type);
//        ArrayList<Integer> q = new ArrayList<Integer>();
//        ArrayList<Integer> p=new ArrayList<Integer>();
//
//        for (int i = 0 ; i < providers.size();i++){
//            q.add(providers.get(i).getQuality());
//
//        }
    //}
}


