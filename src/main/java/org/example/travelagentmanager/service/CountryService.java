package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Client;
import org.example.travelagentmanager.model.Country;
import org.example.travelagentmanager.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 06.11.2024
 */
@Service
public class CountryService {
    private final CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public Optional<Country> getCountryById(int id) {
        return countryRepository.findById(id);
    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    public void updateCountry(Country country, int id) {
        countryRepository.update(country, id);
    }

    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }
}
