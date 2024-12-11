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
import java.util.Optional;

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
    private static final RowMapper<Trip> tripRowMapper = new RowMapper<>() {
        @Override
        public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
            Trip trip = new Trip();
            trip.setId(rs.getInt("id"));
            trip.setTripCost(rs.getBigDecimal("tripcost"));
            trip.setEmployeeId(rs.getInt("employeeid"));
            trip.setRoutesId(rs.getInt("routesid"));
            trip.setDepartureTime(rs.getDate("departuretime"));
            trip.setArrivalTime(rs.getDate("arrivaldate"));
            trip.setTouristCount(rs.getInt("touristcount"));
            trip.setPenaltySize(rs.getBigDecimal("penaltysize"));
            return trip;
        }
    };
    private static final RowMapper<Trip> tripRowMapperUpt = new RowMapper<>() {
        @Override
        public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
            Trip trip = new Trip();
            trip.setId(rs.getInt("id"));
            trip.setTripCost(rs.getBigDecimal("tripcost"));
            String fullName = rs.getString("lastname") + " " +
                    rs.getString("firstname") + " " +
                    rs.getString("surname");
            trip.setEmployeeFullName(fullName);
            trip.setRouteName(rs.getString("routename"));
            //trip.setEmployeeId(rs.getInt("employeeid"));
            //trip.setRoutesId(rs.getInt("routesid"));
            trip.setDepartureTime(rs.getDate("departuretime"));
            trip.setArrivalTime(rs.getDate("arrivaldate"));
            trip.setTouristCount(rs.getInt("touristcount"));
            trip.setPenaltySize(rs.getBigDecimal("penaltysize"));
            return trip;
        }
    };

    @Override
    public Optional<Trip> findById(int id) {
        String sql = "SELECT * FROM trips WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, tripRowMapper, id));
    }

    @Override
    public List<Trip> findAll() {
        String sql = "SELECT " +
                "t.id, t.tripcost, e.lastname, e.firstname, e.surname, " +
                "r.routename, t.departuretime, t.arrivaldate, t.touristcount, t.penaltysize " +
                "FROM touristdb.public.trips t " +
                "join " +
                "touristdb.public.employee e on t.employeeid = e.id " +
                "join routes r on t.routesid = r.id";
        return jdbcTemplate.query(sql, tripRowMapperUpt);
    }

    @Override
    public void save(Trip trip) {
        String sql = "INSERT INTO trips (id, tripcost, employeeid, routesid, departuretime, " +
                "arrivaldate, touristcount, penaltysize) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                trip.getId(),
                trip.getTripCost(),
                trip.getEmployeeId(),
                trip.getRoutesId(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                trip.getPenaltySize());
    }

    @Override
    public void update(Trip trip, int id) {
        String sql  = "UPDATE trips SET tripcost=?, employeeid=?, routesid=?, departuretime=?, " +
                "arrivaldate=?, touristcount=?, penaltysize=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                trip.getTripCost(),
                trip.getEmployeeId(),
                trip.getRoutesId(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                trip.getTouristCount(),
                trip.getPenaltySize(),
                id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM trips WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
