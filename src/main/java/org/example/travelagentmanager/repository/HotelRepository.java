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
import java.util.Optional;

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
    private static final RowMapper<Hotel> hotelRowMapper = (rs, rowNum) -> {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setHotelName(rs.getString("hotelname"));
        hotel.setHotelClass(rs.getInt("hotelclass"));
        return hotel;
    };
    @Override
    public Optional<Hotel> findById(int id) {
        String sql = "select * from hotel where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, hotelRowMapper, id));
    }

    @Override
    public List<Hotel> findAll() {
        String sql = "select * from hotel";
        return jdbcTemplate.query(sql, hotelRowMapper);
    }

    @Override
    public void save(Hotel hotel) {
        String sql = "insert into hotel (hotelname, hotelclass) VALUES (?, ?)";
        jdbcTemplate.update(sql, hotel.getHotelName(), hotel.getHotelClass());
    }

    @Override
    public void update(Hotel hotel, int id) {
        String sql = "update hotel set hotelname = ?, hotelclass = ? where id = ? ";
        jdbcTemplate.update(sql, hotel.getHotelName(), hotel.getHotelClass(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from hotel where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
