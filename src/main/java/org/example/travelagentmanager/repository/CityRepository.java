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
import java.util.Optional;

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
    private static final RowMapper<City> cityRowMapper = (rs, rowNum) -> {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setHotelId(rs.getInt("hotelId"));
        city.setExcursionProgramId(rs.getInt("excursionProgramId"));
        city.setCityName(rs.getString("cityName"));
        return city;
    };

    @Override
    public Optional<City> findById(int id) {
        String sql = "select * from city where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, cityRowMapper, id));
    }

    @Override
    public List<City> findAll() {
        String sql = "select * from city";
        return jdbcTemplate.query(sql, cityRowMapper);
    }

    @Override
    public void save(City city) {
        String sql = "insert into city (hotelId, cityName) values (?,?)";
        jdbcTemplate.update(sql, city.getHotelId(), city.getCityName());
    }

    @Override
    public void update(City city, int id) {
        String sql = "update city set hotelId = ?, cityName = ? where id = ?";
        jdbcTemplate.update(sql, city.getHotelId(), city.getCityName(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from city where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
