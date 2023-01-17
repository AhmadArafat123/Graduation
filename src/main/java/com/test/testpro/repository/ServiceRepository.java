package com.test.testpro.repository;


import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel,Long> {
    List<ServiceModel> findAllByUserName(String userName);
    List<ServiceModel> findAllByPoke(boolean poke);
    List<ServiceModel> findAllByCustomer(Customer customer);
    List<ServiceModel> findAllByProvider(Provider provider);
    ServiceModel findByProviderAndServiceName(Provider p , String name);

}
