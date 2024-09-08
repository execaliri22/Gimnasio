package com.irojas.apirest.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired // se utiliza para realizar la inyección de dependencias.
    private ProductRepository productRepository;

    // Agregar un nuevo producto
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Obtener un producto por ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Eliminar un producto por ID
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // Obtener todos los productos
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

