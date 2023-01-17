package com.test.testpro.Controller;

import com.test.testpro.model.Poke;
import com.test.testpro.model.ServiceModel;
import com.test.testpro.service.PokeService;
import com.test.testpro.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poke")
public class PokeController {
    PokeService service;
    public PokeController(PokeService service) {
        this.service =  service;
    }


    @GetMapping(value = "/getAllPokes/provider")
    public List<Poke> getAllPoks(@RequestParam String userName) {
        return service.getAllPoks(userName);
    }

    @GetMapping(value = "/getAllPokes/customer")
    public List<Poke> getAllPoksForCustomer(@RequestParam  String customerName) {
        return service.getAllPoksForCustomer(customerName);
    }

    @PostMapping(value = "/tryToPokeServie")
        public Poke tryToPokeService(@RequestBody Poke poke ){
        return service.tryTopokeService(poke);
    }
    @PostMapping(value = "/forcePokeService")
    public Poke forcePokeService(@RequestBody Poke poke ){
        return service.forcePokeService(poke);
    }

    @PostMapping(value = "/accepteServie/{id}")
    public Poke pokeService(@PathVariable long id){
        return service.pokeService(id);
    }
    @PostMapping(value = "/rejectServie/{id}")
    public Poke rejectService(@PathVariable long id){
        return service.rejectService(id);
    }
    @PostMapping(value = "/completeService/{id}")
    public Poke completedService(@PathVariable long id){
        return service.completeService(id);
    }



}
