package org.example.travelagentmanager.repository.interfaces;

import org.example.travelagentmanager.model.Country;

import java.util.List;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface CountryRep {
    Country findById(int id);
    List<Country> findAll();
    void save(Country country);
    void update(Country country);
    void deleteById(int id);
}
