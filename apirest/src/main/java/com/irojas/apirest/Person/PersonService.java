package com.irojas.apirest.Person;

import com.irojas.apirest.Document.Document;
import com.irojas.apirest.Document.DocumentRepository;
import com.irojas.apirest.Product.Product;
import com.irojas.apirest.Product.ProductRepository;
import com.irojas.apirest.Gym.Gym;
import com.irojas.apirest.Gym.GymRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepo;
    private final DocumentRepository documentRepo;
    private final ProductRepository productRepo;
    private final GymRepository gymRepo;
//se utilizan para realizar operacioner crud en la base de datos
    public void createPerson(Person person) {
        Document document = person.getDocument(); //se obtiene el documento asociado a la persona
        if (document != null) {
            if (document.getId() == null) {
                documentRepo.save(document);
            }
            document.setPerson(person);//se establece la relacion entre person y docuemnt
        }
        personRepo.save(person);
    }

    public List<Person> getAllPersons() { //devuelve una lista de objetos tipo person
        return personRepo.findAll();
    }

    public void deletePerson(Integer personId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));
    
        // Elimina productos asociados
        List<Product> products = person.getProducts();
        for (Product product : products) {
            productRepo.delete(product);
        }
    
        // Elimina el documento asociado si existe
        Document document = person.getDocument();
        if (document != null) {
            person.setDocument(null);
            documentRepo.delete(document);
        }
        
        // Elimina la relación con gimnasios
        person.getGyms().clear();
        personRepo.save(person);
    
        // Elimina la persona
        personRepo.delete(person);
    }

    public void addProductToPerson(Integer personId, Integer productId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));
    
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productId));
    
        product.setPerson(person);  // Asocia el producto con la persona
        productRepo.save(product);  // Guarda el producto con la nueva asociación
    }
    

    public List<Product> getPersonProducts(Integer personId) { 
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        return person.getProducts();
    }

    public void deleteProductFromPerson(Integer personId, Integer productId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));
    
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productId));
    
        // Verifica si el producto está asociado a la persona
        if (person.getProducts().remove(product)) {
            product.setPerson(null);
            productRepo.delete(product);
            personRepo.save(person); // Guarda los cambios en la persona
        } else {
            throw new RuntimeException("El producto no está asociado a esta persona.");
        }
    }

    // Métodos para manejar la relación con Gym

    public void addGymToPerson(Integer personId, Long gymId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        Gym gym = gymRepo.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gimnasio no encontrado con ID: " + gymId));

        person.getGyms().add(gym);
        personRepo.save(person);
    }

    public List<Gym> getPersonGyms(Integer personId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        return List.copyOf(person.getGyms());
    }

    public void removeGymFromPerson(Integer personId, Long gymId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        Gym gym = gymRepo.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gimnasio no encontrado con ID: " + gymId));

        if (person.getGyms().remove(gym)) {
            personRepo.save(person); // Guarda los cambios en la persona
        } else {
            throw new RuntimeException("El gimnasio no está asociado a esta persona.");
        }
    }
} 