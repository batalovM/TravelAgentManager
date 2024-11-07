package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Hotel;
import org.example.travelagentmanager.interfaces.HotelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Repository
public class HotelRepository implements HotelRep {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public HotelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Hotel> hotelRowMapper = new RowMapper<>() {
        @Override
        public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hotel hotel = new Hotel();
            hotel.setId(rs.getInt("id"));
            hotel.setHotelName(rs.getString("hotelname"));
            hotel.setHotelClass(rs.getInt("hotelclass"));
            return hotel;
        }
    };
    @Override
    public Hotel findById(int id) {
        return null;
    }

    @Override
    public List<Hotel> findAll() {
        return List.of();
    }

    @Override
    public void save(Hotel hotel) {

    }

    @Override
    public void update(Hotel hotel) {

    }

    @Override
    public void deleteById(int id) {

    }
}
