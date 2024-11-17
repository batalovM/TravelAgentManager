package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.Trip;
import org.example.travelagentmanager.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 10.10.2024
 */
@RestController
@RequestMapping("/api/trip")
public class TripController {
    private final TripService tripService;
    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    @GetMapping
    public List<Trip> getAllTrip() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Trip>> getTripById(@PathVariable int id) {
        Optional<Trip> route = tripService.getTripById(id);
        if (route.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(route);
    }

    @PostMapping("/addTrips")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        tripService.addTrip(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable int id, @RequestBody Trip trip) {
        tripService.updateTrip(trip, id);
        return ResponseEntity.ok(trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}
