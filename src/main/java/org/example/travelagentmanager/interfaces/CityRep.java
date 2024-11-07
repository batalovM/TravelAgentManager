package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.City;

import java.util.List;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface CityRep {
    City findById(int id);
    List<City> findAll();
    void save(City city);
    void update(City city);
    void deleteById(int id);
}
