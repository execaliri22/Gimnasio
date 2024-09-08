package com.irojas.apirest.Gym;


import java.util.HashSet;
import java.util.Set;

import com.irojas.apirest.Person.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @ManyToMany(mappedBy = "gyms")
    private Set<Person> persons = new HashSet<>();

    // Constructor vacío necesario para JPA
    public Gym() {}

    // Constructor que inicializa name y location
    public Gym(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}


