package com.test.testpro.Controller;

import com.test.testpro.model.Poke;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    Service service;
    public ServiceController(Service service) {
        this.service =  service;
    }
    @GetMapping(value = "/getAllServicesByName/{userName}")
    public List<ServiceModel> getServiceById(@PathVariable String userName){
        return service.getAllServicesByName(userName);
    }

    @GetMapping(value = "/getAllServices")
    public List<ServiceModel> getAllService() {
        return service.getAllServices();
    }

    @PostMapping(value = "/createService")
    public ServiceModel createService(@RequestBody ServiceModel serviceModel,@RequestParam String userNanme){
        return service.createService(serviceModel,userNanme);
    }


    @DeleteMapping(value = "/deleteService/{id}")
    public String deleteService(@PathVariable long id){
        return service.deleteService(id);
    }
    @PutMapping(value = "/updateService/{id}")
    public void updateService(@PathVariable long id){
        service.updateService(id);
    }
}
