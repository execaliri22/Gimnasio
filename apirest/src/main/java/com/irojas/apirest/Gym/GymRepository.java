package com.irojas.apirest.Gym;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {
    Optional<Gym> findByNameAndLocation(String name, String location);
}
