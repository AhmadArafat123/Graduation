package com.test.testpro.repository;


import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {

    Optional<Provider> findByUserName(String name);
    List<Provider> findAllByAvailabilityAndTypeOfService(boolean av,String type);
    Optional<Provider> findProviderByPhoneNum(String phoneNum);
    Optional<Customer> findCustomerByUserName(String userName);

}
