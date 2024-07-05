package com.irojas.apirest.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Aquí puedes definir métodos adicionales según tus necesidades
}

