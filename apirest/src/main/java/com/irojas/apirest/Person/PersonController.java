package com.irojas.apirest.Person;

import com.irojas.apirest.Product.Product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

 

    
}