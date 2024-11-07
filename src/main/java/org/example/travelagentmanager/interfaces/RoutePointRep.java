package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.RoutePoint;

import java.util.List;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface RoutePointRep {
    RoutePoint findById(int id);
    List<RoutePoint> findAll();
    void save(RoutePoint routePoint);
    void update(RoutePoint routePoint);
    void deleteById(int id);
}
