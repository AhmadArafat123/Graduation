package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Customer;
import com.test.testpro.repository.CustomerRepository;
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
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getUser(String email, String password) {
        Optional<Customer> c = customerRepository.findByEmail(email);
        if (!c.isPresent() || !password.equalsIgnoreCase(c.get().getPassword())) {
            throw new ApiRequestException("Error email or password is wrong");
        }

        return c;

    }

    public void save(Customer user) {
        customerRepository.save(user);
    }

    public void updateUser(long id) {
        Optional<Customer> user = customerRepository.findById(id);
        if (user.isPresent()) {
            Customer u = user.get();
            u.setCity("Nablus");

        }
    }

    public Boolean createUser(Customer user) {
        customerRepository.save(user);
        return true;
    }

    public String deleteUser(long id) {
        Optional<Customer> user = customerRepository.findById(id);
        if (user.isPresent()) {
            customerRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }
}


