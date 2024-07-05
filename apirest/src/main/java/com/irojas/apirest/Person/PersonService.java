package com.irojas.apirest.Person;

import com.irojas.apirest.Document.Document;
import com.irojas.apirest.Document.DocumentRepository;
import com.irojas.apirest.Product.Product;
import com.irojas.apirest.Product.ProductRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepo;
    private final DocumentRepository documentRepo;
    private final ProductRepository productRepo;
   

    public void createPerson(Person person) {
        Document document = person.getDocument();
        if (document != null) {
            if (document.getId() == null) {
                documentRepo.save(document);
            }
            document.setPerson(person);
        }
        personRepo.save(person);
    }

    public void addProductToPerson(Integer personId, Product product) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        product.setPerson(person);
        productRepo.save(product);
    }

    public List<Product> getPersonProducts(Integer personId) {
        Person person = personRepo.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personId));

        return person.getProducts();
    }



   
}