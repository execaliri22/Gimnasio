package com.irojas.apirest.Gym;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GymService {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public Gym createGym(Gym gym) {
        return gymRepository.save(gym);
    }

    public void deleteGym(Long gymId) {
        if (gymRepository.existsById(gymId)) {
            gymRepository.deleteById(gymId);
        } else {
            throw new RuntimeException("Gimnasio no encontrado con ID: " + gymId);
        }
    }

    public Gym getGym(Long gymId) {
        return gymRepository.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gimnasio no encontrado con ID: " + gymId));
    }

    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }
}
