package com.irojas.apirest.Person;

import com.irojas.apirest.Product.Product;
import com.irojas.apirest.Gym.Gym;

import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable Integer personId) {
        personService.deletePerson(personId);
    }

    // Asociar un producto con una persona usando IDs
    @PostMapping("/{personId}/products/{productId}")
    public ResponseEntity<Void> addProductToPerson(@PathVariable Integer personId, @PathVariable Integer productId) {
        personService.addProductToPerson(personId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{personId}/products")
    public List<Product> getPersonProducts(@PathVariable Integer personId) {
        return personService.getPersonProducts(personId);
    }

    @DeleteMapping("/{personId}/products/{productId}")
    public void deleteProductFromPerson(@PathVariable Integer personId, @PathVariable Integer productId) {
        personService.deleteProductFromPerson(personId, productId);
    }

    // Métodos para manejar la relación con Gym

    @PostMapping("/{personId}/gyms/{gymId}")
    public void addGymToPerson(@PathVariable Integer personId, @PathVariable Long gymId) {
        personService.addGymToPerson(personId, gymId);
    }

    @GetMapping("/{personId}/gyms")
    public ResponseEntity<List<Gym>> getPersonGyms(@PathVariable Integer personId) {
        List<Gym> gyms = personService.getPersonGyms(personId);
        return ResponseEntity.ok(gyms);
    }

    @DeleteMapping("/{personId}/gyms/{gymId}")
    public void removeGymFromPerson(@PathVariable Integer personId, @PathVariable Long gymId) {
        personService.removeGymFromPerson(personId, gymId);
    }
} 