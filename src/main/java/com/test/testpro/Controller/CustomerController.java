package com.test.testpro.Controller;

import com.test.testpro.model.Customer;
import com.test.testpro.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(value = "/getCustomer/{id}")
    public Optional<Customer> getUserById(@PathVariable long id){
        return customerService.getUser(id);
    }

    @PostMapping(value = "/createCustomer/")
    public Customer createUser(@RequestBody Customer user){
        return customerService.createUser(user);
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public String deleteUser(@PathVariable long id){
        return customerService.deleteUser(id);
    }


    @PutMapping(value = "/updateCustomer/{id}")
    public void updateUser(@PathVariable long id){
        customerService.updateUser(id);
    }

    @GetMapping("/suggestions/{letter}")
    public List<String> fetchSuggestions(@PathVariable String letter) {
        return customerService.fetchSuggestions(letter);
    }

}
