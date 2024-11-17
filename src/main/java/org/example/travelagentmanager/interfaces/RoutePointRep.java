package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.RoutePoint;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface RoutePointRep {
    Optional<RoutePoint> findById(int id);
    List<RoutePoint> findAll();
    void save(RoutePoint routePoint);
    void update(RoutePoint routePoint, int id);
    void deleteById(int id);
}
