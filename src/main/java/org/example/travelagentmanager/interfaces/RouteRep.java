package org.example.travelagentmanager.interfaces;

import org.example.travelagentmanager.model.Route;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 07.11.2024
 */
public interface RouteRep {
    Optional<Route> findById(int id);
    List<Route> findAll();
    void save(Route route);
    void update(Route route, int id);
    void deleteById(int id);
}
