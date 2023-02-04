package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Customer;
import com.test.testpro.model.EmailEntity;
import com.test.testpro.model.Rate;
import com.test.testpro.repository.CustomerRepository;
import com.test.testpro.repository.RateRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@EnableTransactionManagement
@EnableRetry
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RateRepository rateRepository;

    private final EmailService emailService;
    public Random random;

    public CustomerService(CustomerRepository customerRepository, RateRepository rateRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.rateRepository = rateRepository;
        this.emailService = emailService;
      random = new Random();
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
        String name= user.getUserName();
        Optional<Customer> c=customerRepository.findCustomerByUserName(name);
        if(c.isPresent()){
            throw new ApiRequestException("This is a used userName Please Enter anotherone");
        }

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
    public String ForgetPassword(String email) {
        Optional<Customer> cu = customerRepository.findByEmail(email);
        if (!cu.isPresent()){
            throw new ApiRequestException("This is not a valid email");
        }
        Customer customer=cu.get();
        Integer num=(random.nextInt(10000000 - 1000000) + 1000000);
        String newPass= num.toString();

        EmailEntity emailEntity = new EmailEntity(customer.getEmail(),
                "This is your new password "+newPass,
                "Reset Password",null);
        emailService.sendSimpleMail(emailEntity);
        customer.setPassword(newPass);
        return "A new password sent on your email";

    }


}


