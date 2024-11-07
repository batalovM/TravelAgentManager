package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Country;
import org.example.travelagentmanager.interfaces.CountryRep;
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
public class CountryRepository implements CountryRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Country> countryRowMapper = new RowMapper<>() {
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getInt("id"));
            country.setCountryName(rs.getString("countryname"));
            return country;
        }
    };
    @Override
    public Country findById(int id) {
        return null;
    }

    @Override
    public List<Country> findAll() {
        return List.of();
    }

    @Override
    public void save(Country country) {

    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deleteById(int id) {

    }
}
