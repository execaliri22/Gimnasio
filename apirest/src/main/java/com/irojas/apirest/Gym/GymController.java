package com.irojas.apirest.Gym;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity<Gym> createGym(@RequestBody Gym gym) {
        Gym createdGym = gymService.createGym(gym);
        return ResponseEntity.ok(createdGym);
    }

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms() {
        List<Gym> gyms = gymService.getAllGyms();
        return ResponseEntity.ok(gyms);
    }

    @GetMapping("/{gymId}")
    public ResponseEntity<Gym> getGym(@PathVariable Long gymId) {
        Gym gym = gymService.getGym(gymId);
        return ResponseEntity.ok(gym);
    }

    @DeleteMapping("/{gymId}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long gymId) {
        gymService.deleteGym(gymId);
        return ResponseEntity.noContent().build();
    }
}