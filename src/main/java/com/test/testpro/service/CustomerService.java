package com.test.testpro.service;

import com.test.testpro.model.Customer;
import com.test.testpro.repository.CustomerRepository;
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
public class CustomerService {

    private final CustomerRepository userRepository;

    public CustomerService(CustomerRepository userRepository ) {
        this.userRepository = userRepository;
    }
    public Optional<Customer> getUser(String email,String password){
       Optional<Customer> c=  userRepository.findByEmail(email);
       if(c.isPresent()){
           if(password.equalsIgnoreCase(c.get().getPassword()))
               return c;
       }
       return c;
    }

    public void save(Customer user) {
        userRepository.save(user);
    }

    public void updateUser(long id) {
        Optional<Customer> user = userRepository.findById(id);
        if(user.isPresent()){
            Customer u = user.get();
            u.setCity("Nablus");

        }
    }

    public Boolean createUser(Customer user) {
        userRepository.save(user);
        return true;
    }

    public String deleteUser(long id) {
        Optional<Customer> user=userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }
}


