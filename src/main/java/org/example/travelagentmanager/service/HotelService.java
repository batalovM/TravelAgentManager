package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Hotel;
import org.example.travelagentmanager.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    public Hotel getHotelById(int id) {
        return hotelRepository.findById(id);
    }
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }
    public void updateHotel(Hotel hotel) {
        hotelRepository.update(hotel);
    }
    public void deleteHotel(int id) {
        hotelRepository.deleteById(id);
    }
}
