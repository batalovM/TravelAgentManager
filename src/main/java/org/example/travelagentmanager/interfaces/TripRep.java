package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Trip;

import java.util.List;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface TripRep {
    Trip findById(int id);
    List<Trip> findAll();
    void save(Trip trip);
    void update(Trip trip);
    void deleteById(int id);
}
