package com.irojas.apirest.Person;

import com.irojas.apirest.Gym.Gym;
import com.irojas.apirest.Product.Product;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void createPerson(@RequestBody Person person) {
        personService.createPerson(person);
    }

    @PostMapping("/{personId}/products")
    public void addProductToPerson(@PathVariable Integer personId, @RequestBody Product product) {
        personService.addProductToPerson(personId, product);
    }

    @GetMapping("/{personId}/products")
    public List<Product> getPersonProducts(@PathVariable Integer personId) {
        return personService.getPersonProducts(personId);
    }

    @PostMapping("/{personId}/gyms")
    public void addGymToPerson(@PathVariable Integer personId, @RequestBody Gym gym) {
        personService.addGymToPerson(personId, gym);
    }

    @GetMapping("/{personId}/gyms")
    public Set<Gym> getPersonGyms(@PathVariable Integer personId) {
        return personService.getPersonGyms(personId);
    }
}
