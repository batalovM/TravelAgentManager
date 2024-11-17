package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.City;
import org.example.travelagentmanager.service.CityService;
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
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> getCityById(@PathVariable int id) {
        Optional<City> city = cityService.getCityById(id);
        if(city.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(city);
    }
    @PostMapping("/addCity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        cityService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable int id, @RequestBody City city) {
        cityService.updateCity(city, id);
        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
