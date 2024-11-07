package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.Route;
import org.example.travelagentmanager.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Service
public class RouteService {
    private final RouteRepository routeRepository;
    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
    public Route getRoute(int id) {
        return routeRepository.findById(id);
    }
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    public void addRoute(Route route) {
        routeRepository.save(route);
    }
    public void updateRoute(Route route) {
        routeRepository.update(route);
    }
    public void deleteRoute(int id) {
        routeRepository.deleteById(id);
    }
}
