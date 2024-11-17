package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.Hotel;
import org.example.travelagentmanager.service.HotelService;
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
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    @Autowired
    public HotelController(final HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hotel>> getHotelById(@PathVariable final int id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        if (hotel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(hotel);
    }
    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody final Hotel hotel) {
        hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable final int id, @RequestBody Hotel hotel) {
        hotelService.updateHotel(hotel, id);
        return ResponseEntity.ok(hotel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable final int id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
