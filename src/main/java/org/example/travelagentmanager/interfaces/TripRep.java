package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Trip;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface TripRep {
    Optional<Trip> findById(int id);
    List<Trip> findAll();
    void save(Trip trip);
    void update(Trip trip, int id);
    void deleteById(int id);
}
