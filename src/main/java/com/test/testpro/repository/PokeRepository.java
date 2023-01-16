package com.test.testpro.repository;


import com.test.testpro.model.Poke;
import com.test.testpro.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokeRepository extends JpaRepository<Poke,Long> {
    List<Poke> findAllByServiceUserName(String userName);
    List<Poke> findAllByNameOfCustomer(String userName);



}
