package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Country;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 06.11.2024
 */
public interface CountryRep {
    Optional<Country> findById(int id);
    List<Country> findAll();
    void save(Country country);
    void update(Country country, int id);
    void deleteById(int id);
}
