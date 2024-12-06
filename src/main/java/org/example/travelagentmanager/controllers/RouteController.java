package org.example.travelagentmanager.controllers;


import org.example.travelagentmanager.model.Route;
import org.example.travelagentmanager.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author batal
 * @Date 10.10.2024
 */
@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;
    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Route>> getRouteById(@PathVariable int id) {
        Optional<Route> route = routeService.getRouteById(id);
        if (route.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(route);
    }

    @PostMapping("/addRoutes")
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        routeService.addRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable int id, @RequestBody Route route) {
        routeService.updateRoute(route, id);
        return ResponseEntity.ok(route);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

}
