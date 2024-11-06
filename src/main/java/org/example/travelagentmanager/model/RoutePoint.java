package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author batal
 * @Date 10.10.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoutePoint {
    private int id;
    private int routeId;
    private int routePointsId;
}