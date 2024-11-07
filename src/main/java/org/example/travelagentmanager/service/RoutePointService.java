package org.example.travelagentmanager.service;

import org.example.travelagentmanager.model.RoutePoint;
import org.example.travelagentmanager.repository.RoutePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author batal
 * @Date 07.11.2024
 */
@Service
public class RoutePointService {
    private final RoutePointRepository routeRepository;
    @Autowired
    public RoutePointService(RoutePointRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
    public RoutePoint getRoutePoint(int id) {
        return routeRepository.findById(id);
    }
    public List<RoutePoint> getAllRoutePoints() {
        return routeRepository.findAll();
    }
    public void addRoutePoint(RoutePoint routePoint) {
        routeRepository.save(routePoint);
    }
    public void updateRoutePoint(RoutePoint routePoint) {
        routeRepository.update(routePoint);
    }
    public void deleteRoutePoint(int id) {
        routeRepository.deleteById(id);
    }
}