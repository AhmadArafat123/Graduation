package com.test.testpro.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.test.testpro.model.Customer;
import com.test.testpro.model.Note;
import com.test.testpro.service.CustomerService;
import com.test.testpro.service.FirebaseMessagingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CustomerController {
    CustomerService customerService;
    FirebaseMessagingService firebaseMessagingService;

    public CustomerController(CustomerService customerService,FirebaseMessagingService firebaseMessagingService) {
        this.customerService = customerService;
        this.firebaseMessagingService=firebaseMessagingService;
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

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,@RequestParam String topic) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, topic);
    }

}
