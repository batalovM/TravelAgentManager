package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Country;
import org.example.travelagentmanager.interfaces.CountryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

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
    private static final RowMapper<Country> countryRowMapper = (rs, rowNum) -> {
        Country country = new Country();
        country.setId(rs.getInt("id"));
        country.setCountryName(rs.getString("countryname"));
        return country;
    };
    @Override
    public Optional<Country> findById(int id) {
        String sql = "select * from country where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, countryRowMapper, id));
    }

    @Override
    public List<Country> findAll() {
        String sql = "select * from country";
        return jdbcTemplate.query(sql, countryRowMapper);
    }

    @Override
    public void save(Country country) {
        String sql = "insert into country (countryname) values (?)";
        jdbcTemplate.update(sql, country.getCountryName());
    }

    @Override
    public void update(Country country, int id) {
        String sql = "update country set countryname = ? where id = ?";
        jdbcTemplate.update(sql, country.getCountryName(), id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from country where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
