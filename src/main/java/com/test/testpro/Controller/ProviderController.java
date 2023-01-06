package com.test.testpro.Controller;

import com.test.testpro.model.Provider;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ProviderController {
    com.test.testpro.service.providerService providerService;

    public ProviderController(com.test.testpro.service.providerService providerService) {
        this.providerService = providerService;
    }
    @GetMapping(value = "/getProvider/{id}")
    public Optional<Provider> getProvider(@PathVariable long id){
        return providerService.getProvider(id);
    }
    @PostMapping(value = "/createProvider/")
    public Provider createProvider(@RequestBody Provider provider){
        return providerService.createProvider(provider);
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
