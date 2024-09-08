package com.irojas.apirest.Person;

import com.irojas.apirest.Document.Document;
import com.irojas.apirest.Gym.Gym;
import com.irojas.apirest.Product.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "document_id")
    
    private Document document;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    
    private List<Product> products;

     @ManyToMany
    @JoinTable(
        name = "person_gym",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "gym_id")
    )
    private Set<Gym> gyms = new HashSet<>();

}