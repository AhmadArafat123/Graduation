package com.test.testpro.Controller;

import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ServiceController {
    Service service;
    public ServiceController(Service service) {
        this.service =  service;
    }
    @GetMapping(value = "/getService/{id}")
    public Optional<ServiceModel> getServiceById(@PathVariable long id){
        return service.getService(id);
    }
    @PostMapping(value = "/createService/")
    public ServiceModel createService(@RequestBody ServiceModel serviceModel){
        return service.createService(serviceModel);
    }
    @DeleteMapping(value = "/deleteCustomer/{id}")
    public String deleteService(@PathVariable long id){
        return service.deleteService(id);
    }
    @PutMapping(value = "/updateService/{id}")
    public void updateService(@PathVariable long id){
        service.updateService(id);
    }
}
