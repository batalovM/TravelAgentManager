package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Trip;
import org.example.travelagentmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Service
public class TripService {
    private final TripRepository tripRepository;
    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
    public Trip getTrip(int id) {
        return tripRepository.findById(id);
    }
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
    public void addTrip(Trip trip) {
        tripRepository.save(trip);
    }
    public void updateTrip(Trip trip) {
        tripRepository.update(trip);
    }
    public void deleteTrip(int id) {
        tripRepository.deleteById(id);
    }
}
