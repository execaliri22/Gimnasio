package com.irojas.apirest.Person;

import com.irojas.apirest.Gym.Gym;
import com.irojas.apirest.Product.Product;

import org.springframework.http.ResponseEntity;//se utiliza para construir respuestas HTTP 
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

        @GetMapping// @GetMapping: Indica que este método responderá a solicitudes HTTP GET. Es para obtener información.
    public ResponseEntity<List<Person>> getAllPersons() {//El método devuelve una respuesta que incluye el cuerpo (una lista de personas)
        List<Person> persons = personService.getAllPersons(); // Llama a un servicio que obtiene la lista de todas las personas desde la base de datos.
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable Integer personId) {
        personService.deletePerson(personId);
    }


    
    @PostMapping("/{personId}/products")
    public void addProductToPerson(@PathVariable Integer personId, @RequestBody Product product) {
        personService.addProductToPerson(personId, product);
    }

    @GetMapping("/{personId}/products")
    public List<Product> getPersonProducts(@PathVariable Integer personId) {
        return personService.getPersonProducts(personId);
    }

    @DeleteMapping("/{personId}/products/{productId}")
    public void deleteProductFromPerson(@PathVariable Integer personId, @PathVariable Integer productId) {
        personService.deleteProductFromPerson(personId, productId);
    }


    @PostMapping("/{personId}/gyms")
    public void addGymToPerson(@PathVariable Integer personId, @RequestBody Gym gym) {
        personService.addGymToPerson(personId, gym);
    }

    @GetMapping("/{personId}/gyms")
    public Set<Gym> getPersonGyms(@PathVariable Integer personId) {
        return personService.getPersonGyms(personId);
    }

    @DeleteMapping("/{personId}/gyms/{gymId}")
    public void deleteGymFromPerson(@PathVariable Integer personId, @PathVariable Integer gymId) {
        personService.deleteGymFromPerson(personId, gymId);
    }
    
}
