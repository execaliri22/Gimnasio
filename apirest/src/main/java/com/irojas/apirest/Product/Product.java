package com.irojas.apirest.Product;

import jakarta.persistence.*;
import com.irojas.apirest.Person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
