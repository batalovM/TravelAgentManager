package org.example.travelagentmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author batal
 * @Date 10.10.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    private int id;
    private BigDecimal tripCost;
    private int employeeId;
    private int routesId;
    private String departureTime;
    private String arrivalTime;
    private int touristCount;
    private BigDecimal penaltySize;
}
