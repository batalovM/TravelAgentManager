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
import java.util.Optional;

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
    private static final RowMapper<RoutePoint> RoutePointRowMapper = (rs, rowNum) -> {
        RoutePoint routePoint = new RoutePoint();
        routePoint.setId(rs.getInt("id"));
        routePoint.setExcursionProgramId(rs.getInt("excursionprogramid"));
        routePoint.setHotelId(rs.getInt("hotelid"));
        routePoint.setRouteName(rs.getString("routename"));
        routePoint.setDurationAtPoint(rs.getInt("durationatpoint"));
        return routePoint;
    };

    @Override
    public Optional<RoutePoint> findById(int id) {
        String sql = "select * from routepoints where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, RoutePointRowMapper, id));
    }

    @Override
    public List<RoutePoint> findAll() {
        String sql = "select * from routepoints";
        return jdbcTemplate.query(sql, RoutePointRowMapper);
    }

    @Override
    public void save(RoutePoint routePoint) {
        String sql = "insert into routepoints values (" +
                "id=?, excursionprogramid=?, cityid=?, hotelid=?, routename=?, durationatpoint=?)";
        jdbcTemplate.update(
                sql, routePoint.getId(), routePoint.getExcursionProgramId(),
                routePoint.getCityId(), routePoint.getHotelId(), routePoint.getRouteName(),
                routePoint.getDurationAtPoint()
        );
    }

    @Override
    public void update(RoutePoint routePoint, int id) {
        String sql = "update routepoints set excursionprogramid=?, cityid=?, hotelid=?, routename=?, durationatpoint=? where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from routepoints where id = ?";
        jdbcTemplate.update(sql, id);
    }

}

