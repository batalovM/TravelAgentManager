package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.City;
import org.example.travelagentmanager.interfaces.CityRep;
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
public class CityRepository implements CityRep {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<City> cityRowMapper = new RowMapper<>() {
        @Override
        public City mapRow(ResultSet rs, int rowNum) throws SQLException {
            City city = new City();
            city.setId(rs.getInt("id"));
            city.setHotelId(rs.getInt("hotelId"));
            city.setExcursionProgramId(rs.getInt("excursionProgramId"));
            city.setCityName(rs.getString("cityName"));
            return city;
        }
    };

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public List<City> findAll() {
        return List.of();
    }

    @Override
    public void save(City city) {

    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(int id) {

    }
}
