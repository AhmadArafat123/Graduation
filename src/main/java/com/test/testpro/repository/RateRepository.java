package com.test.testpro.repository;


import com.test.testpro.model.Customer;
import com.test.testpro.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {
    List<Rate> findAllByProviderUserName(String name);


}
