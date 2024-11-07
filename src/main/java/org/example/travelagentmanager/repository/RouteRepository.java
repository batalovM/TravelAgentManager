package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Route;
import org.example.travelagentmanager.interfaces.RouteRep;
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
public class RouteRepository implements RouteRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RouteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Route> RouteRowMapper = new RowMapper<>() {
        @Override
        public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
            Route route = new Route();
            route.setId(rs.getInt("id"));
            route.setCountryId(rs.getInt("countryid"));
            route.setRouteName(rs.getString("routename"));
            route.setDuration(rs.getInt("duration"));
            return route;
        }
    };
    @Override
    public Route findById(int id) {
        return null;
    }

    @Override
    public List<Route> findAll() {
        return List.of();
    }

    @Override
    public void save(Route route) {

    }

    @Override
    public void update(Route route) {

    }

    @Override
    public void deleteById(int id) {

    }
}
