package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Hotel;

import java.util.List;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface HotelRep {
    Hotel findById(int id);
    List<Hotel> findAll();
    void save(Hotel hotel);
    void update(Hotel hotel);
    void deleteById(int id);
}
