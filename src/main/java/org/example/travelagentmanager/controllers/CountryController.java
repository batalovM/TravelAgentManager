package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.Country;
import org.example.travelagentmanager.service.CountryService;
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
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountry();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Country>> getCountryById(@PathVariable int id) {
        Optional<Country> country = countryService.getCountryById(id);
        if(country.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable int id, @RequestBody Country country) {
        countryService.updateCountry(country, id);
        return ResponseEntity.ok(country);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
