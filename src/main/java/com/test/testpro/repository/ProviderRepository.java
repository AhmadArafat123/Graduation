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
    Optional<Provider> findProviderByPhoneNum(String phoneNum);
    Optional<Provider> findProviderByEmail(String userName);

}
