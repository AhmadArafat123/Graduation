package com.test.testpro.Controller;

import com.test.testpro.model.Customer;
import com.test.testpro.model.Provider;
import com.test.testpro.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }
    @GetMapping(value = "/getProvider/{id}")
    public Optional<com.test.testpro.model.Provider> getProvider(@PathVariable long id){
        return providerService.getProvider(id);
    }

    @GetMapping(value = "/getProvider/{email}/{password}")
    public Optional<Provider> getUserById(@PathVariable String email, @PathVariable String password){
        return providerService.getProvidertoLog(email,password);
    }

    @GetMapping(value = "/getProviderbyName")
    public Optional<com.test.testpro.model.Provider> getProviderByName(@RequestParam String name){
        return providerService.getProviderByName(name);
    }
    @PostMapping(value = "/createProvider")
    public com.test.testpro.model.Provider createProvider(@RequestBody com.test.testpro.model.Provider provider){
        return this.providerService.createProvider(provider);
    }
    @DeleteMapping(value = "/deleteProvider/{id}")
    public String deleteProvider(@PathVariable long id){
        return providerService.deleteProvider(id);
    }
    @PutMapping(value = "/updateProvider/{id}")
    public void updateProvider(@PathVariable long id){
        providerService.updateServiceProvider(id);
    }
}
