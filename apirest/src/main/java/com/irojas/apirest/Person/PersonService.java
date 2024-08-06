package com.irojas.apirest.Person;

import com.irojas.apirest.Document.Document;
import com.irojas.apirest.Document.DocumentRepository;
import com.irojas.apirest.Product.Product;
import com.irojas.apirest.Product.ProductRepository;

import jakarta.transaction.Transactional;//es una anotación en Spring que indica que un método debe ejecutarse dentro de una transacción. 

import com.irojas.apirest.Gym.Gym;
import com.irojas.apirest.Gym.GymRepository;

import org.springframework.stereotype.Service;//indica que esta clase es un servidor de spring
import lombok.RequiredArgsConstructor;//se genera un contructor automaticamewnte con sus argumentos

import java.util.List;//define una lista de productos
import java.util.Set;//define un conjunto de gimnasios

@Service//indica que esta clase es un servidor de spring
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepo;
    private final DocumentRepository documentRepo;
    private final ProductRepository productRepo;
    private final GymRepository gymRepo;
    //son repositorios que se utilizan para realizar operaciones crud en la base de datos

    public void createPerson(Person person) {
        Document document = person.getDocument();//obtiene el documento asociado a la persona
        if (document != null) {//verifica que el person tenga documento
            if (document.getId() == null) {
                documentRepo.save(document);
            }
            document.setPerson(person);//establece la relacion entre el documento y la persona
        }
        personRepo.save(person);//guarda la persona en el repo de personas
    }


    public List<Person> getAllPersons() {  // el método devolverá una lista de objetos de tipo Person.
        return personRepo.findAll();
    }

    

    public void deletePerson(Integer personId) {
            // Busca la persona por ID. Si no se encuentra, lanza una excepción.
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

      
        // Obtiene el documento asociado a la persona.
    Document document = person.getDocument();
    if (document != null) { // Verifica si el documento existe.
        person.setDocument(null); // Desasocia el documento de la persona.
        documentRepo.delete(document); // Elimina el documento de la base de datos.
    }

 // Recupera todos los productos asociados a la persona.
        List<Product> products = productRepo.findByPerson(person);
        productRepo.deleteAll(products);// Elimina todos los productos encontrados.

        Set<Gym> gyms = person.getGyms();
        if (gyms != null) {
            gyms.forEach(gym -> gym.getPersons().remove(person));
            gymRepo.saveAll(gyms); 
        }

        personRepo.delete(person);
    }



    public void addProductToPerson(Integer personId, Product product) {
        Person person = personRepo.findById(personId)//buca la persona por su id
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        product.setPerson(person);//establece la relacion entre el producto y la persona
        productRepo.save(product);//se guarda el producto en el repositorio de productos
    }

    public List<Product> getPersonProducts(Integer personId) { 
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        return person.getProducts();
    }


    public void deleteProductFromPerson(Integer personId, Integer productId) {
        // Busca la persona por ID. Si no se encuentra, lanza una excepción.
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));
    
        // Busca el producto por ID. Si no se encuentra, lanza una excepción.
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productId));
    
        // Intenta eliminar el producto de la lista de productos de la persona.
        if (person.getProducts().remove(product)) { // Si el producto fue encontrado y eliminado.
            product.setPerson(null); // Desasocia la persona del producto.
            productRepo.delete(product); // Elimina el producto de la base de datos.
            personRepo.save(person); // Guarda los cambios en la persona.
        } else {
            // Si el producto no estaba asociado a la persona, lanza una excepción.
            throw new RuntimeException("El producto no está asociado a esta persona.");
        }
    }
    

   

    public void addGymToPerson(Integer personId, Gym gym) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));
    
        Gym existingGym = gymRepo.findByNameAndLocation(gym.getName(), gym.getLocation())
                .orElse(gym);
    
        if (existingGym.getId() == null) {
            gymRepo.save(existingGym);
        }
    
        person.getGyms().add(existingGym);
        personRepo.save(person);
    }
    
    public Set<Gym> getPersonGyms(Integer personId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        return person.getGyms();
    }
   

    
    @Transactional //es una anotación en Spring que indica que un método debe ejecutarse dentro de una transacción. 
public void deleteGymFromPerson(Integer personId, Integer gymId) {
    // Busca la persona por ID. Si no se encuentra, lanza una excepción.
    Person person = personRepo.findById(personId)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

    // Busca el gimnasio por ID. Si no se encuentra, lanza una excepción.
    Gym gym = gymRepo.findById(gymId)
            .orElseThrow(() -> new RuntimeException("Gimnasio no encontrado con ID: " + gymId));

    // Verifica si el gimnasio está asociado a la persona.
    if (person.getGyms().contains(gym)) {
        // Elimina el gimnasio de la lista de gimnasios de la persona.
        person.getGyms().remove(gym);
        // Elimina la persona de la lista de personas del gimnasio.
        gym.getPersons().remove(person);

        // Guarda los cambios en la persona y el gimnasio.
        personRepo.save(person);
        gymRepo.save(gym);
    }
}

}





    


   

