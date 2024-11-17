package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.City;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface CityRep {
    Optional<City> findById(int id);
    List<City> findAll();
    void save(City city);
    void update(City city, int id);
    void deleteById(int id);
}
