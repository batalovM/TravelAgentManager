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
import java.util.Optional;

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
    private static final RowMapper<Route> routeRowMapper = (rs, rowNum) -> {
        Route route = new Route();
        route.setId(rs.getInt("id"));
        route.setCountryId(rs.getInt("countryid"));
        route.setRouteName(rs.getString("routename"));
        route.setDuration(rs.getInt("duration"));
        return route;
    };

    private static final RowMapper<Route> routeRowMapperUpt = ((rs, rowNum) -> {
       Route route = new Route();
       route.setId(rs.getInt("id"));
       route.setCountryName(rs.getString("countryname"));
       route.setRouteName(rs.getString("routename"));
       route.setDuration(rs.getInt("duration"));
       return route;
    });

    @Override
    public Optional<Route> findById(int id) {
        String sql = "SELECT * FROM routes WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, routeRowMapper, id));
    }

    @Override
    public List<Route> findAll() {
        String sql = "SELECT\n" +
                "    routes.id,\n" +
                "    country.countryname AS countryname,\n" +
                "    routes.routename,\n" +
                "    routes.duration\n" +
                "FROM\n" +
                "    routes\n" +
                "        LEFT JOIN\n" +
                "    country ON routes.countryid = country.id;\n" +
                "\n";
        return jdbcTemplate.query(sql, routeRowMapperUpt);
    }

    @Override
    public void save(Route route) {
        String sql = "INSERT INTO routes (countryid, routename, duration) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                route.getCountryId(),
                route.getRouteName(),
                route.getDuration());
    }

    @Override
    public void update(Route route, int id) {
        String sql = "UPDATE routes SET countryid=?, routename=?, duration=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                route.getCountryId(),
                route.getRouteName(),
                route.getDuration(),
                id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM routes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
