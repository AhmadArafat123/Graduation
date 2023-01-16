package com.test.testpro.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.test.testpro.model.Customer;
import com.test.testpro.model.Note;
import com.test.testpro.service.CustomerService;
import com.test.testpro.service.FirebaseMessagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/getCustomer/{email}/{password}")
    public Optional<Customer> getUserById(@PathVariable String email,@PathVariable String password){
        return customerService.getUser(email,password);
    }

    @PostMapping(value = "/createCustomer/")
    public ResponseEntity createUser(@RequestBody Customer user){
        return ResponseEntity.ok(customerService.createUser(user));
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
