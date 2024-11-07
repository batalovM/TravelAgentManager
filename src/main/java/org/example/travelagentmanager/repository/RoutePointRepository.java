package org.example.travelagentmanager.repository;

import org.example.travelagentmanager.model.RoutePoint;
import org.example.travelagentmanager.interfaces.RoutePointRep;
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
public class RoutePointRepository implements RoutePointRep {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RoutePointRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final RowMapper<RoutePoint> RoutePointRowMapper = new RowMapper<>() {
        @Override
        public RoutePoint mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoutePoint routePoint = new RoutePoint();
            routePoint.setId(rs.getInt("id"));
            routePoint.setExcursionProgramId(rs.getInt("excursionprogramid"));
            routePoint.setHotelId(rs.getInt("hotelid"));
            routePoint.setRouteName(rs.getString("routename"));
            routePoint.setDurationAtPoint(rs.getInt("durationatpoint"));
            return routePoint;
        }
    };
    @Override
    public RoutePoint findById(int id) {
        return null;
    }

    @Override
    public List<RoutePoint> findAll() {
        return List.of();
    }

    @Override
    public void save(RoutePoint routePoint) {

    }

    @Override
    public void update(RoutePoint routePoint) {

    }

    @Override
    public void deleteById(int id) {

    }
}
