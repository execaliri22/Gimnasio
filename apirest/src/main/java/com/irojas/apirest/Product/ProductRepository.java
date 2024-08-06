package com.irojas.apirest.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.apirest.Person.Person;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByPerson(Person person);
    // Aquí puedes definir métodos adicionales según tus necesidades
}

