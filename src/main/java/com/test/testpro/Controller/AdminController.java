package com.test.testpro.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.test.testpro.model.Customer;
import com.test.testpro.model.Note;
import com.test.testpro.model.Provider;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.AdminService;
import com.test.testpro.service.FirebaseMessagingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final  AdminService adminService;
    private final  FirebaseMessagingService firebaseMessagingService;

    public AdminController(AdminService adminService, FirebaseMessagingService firebaseMessagingService) {
        this.adminService = adminService;

        this.firebaseMessagingService = firebaseMessagingService;
    }

    @GetMapping(value = "/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return adminService.getCustomers();
    }

    @GetMapping(value = "/getAllProviders")
    public List<Provider> getAllProvides() {
        return adminService.getProviders();
    }

    @GetMapping(value = "/getAllServices")
    public List<ServiceModel> getAllServices() {
        return adminService.getAllServices();
    }

    @GetMapping(value = "/getAllRequests")
    public List<Provider> getAllRequests() {
        return adminService.getRequests();
    }

    @DeleteMapping(value = "/deleteCustomer/{id}")
    public String deleteUser(@PathVariable long id) {
        return adminService.deleteCustomer(id);
    }

    @DeleteMapping(value = "/deleteProvider/{id}")
    public String deleteProvider(@PathVariable long id) {
        return adminService.deleteProvider(id);
    }

    @PutMapping(value = "/acceptProvider/{id}")
    public String updateUser(@PathVariable long id) {
        return adminService.acceptProvider(id);
    }

    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note, @RequestParam String topic) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, topic);
    }

}
