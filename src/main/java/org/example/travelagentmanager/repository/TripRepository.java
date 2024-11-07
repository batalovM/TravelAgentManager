package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.Trip;
import org.example.travelagentmanager.interfaces.TripRep;
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
public class TripRepository implements TripRep {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public TripRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<Trip> TripRowMapper = new RowMapper<>() {
        @Override
        public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
            Trip trip = new Trip();
            trip.setId(rs.getInt("id"));
            trip.setTripCost(rs.getBigDecimal("tripcost"));
            trip.setEmployeeId(rs.getInt("employeeid"));
            trip.setRoutesId(rs.getInt("routesid"));
            trip.setDepartureTime(rs.getDate("arrivaldated"));
            trip.setTouristCount(rs.getInt("touristcount"));
            trip.setPenaltySize(rs.getBigDecimal("penaltysize"));
            return trip;
        }
    };

    @Override
    public Trip findById(int id) {
        return null;
    }

    @Override
    public List<Trip> findAll() {
        return List.of();
    }

    @Override
    public void save(Trip trip) {

    }

    @Override
    public void update(Trip trip) {

    }

    @Override
    public void deleteById(int id) {

    }
}
