package com.irojas.apirest.Document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irojas.apirest.Person.Person;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    List<Document> findByPerson(Person person);
}

