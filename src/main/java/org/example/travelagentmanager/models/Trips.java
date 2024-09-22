package org.example.travelagentmanager.models;

import java.math.BigDecimal;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Туристические поездки
public class Trips {
    private int id;
    private int routeId;
    private int companyRepresentativeId;
    private BigDecimal tripCost;
    private String departureTime;
    private String arrivalDate;
    private int touristCount;
    private BigDecimal penaltySize;
    public Trips() {}

    public Trips(int id, int routeId, int companyRepresentativeId, BigDecimal tripCost, String departureTime, String arrivalDate, int touristCount, BigDecimal penaltySize) {
        this.id = id;
        this.routeId = routeId;
        this.companyRepresentativeId = companyRepresentativeId;
        this.tripCost = tripCost;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.touristCount = touristCount;
        this.penaltySize = penaltySize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getCompanyRepresentativeId() {
        return companyRepresentativeId;
    }

    public void setCompanyRepresentativeId(int companyRepresentativeId) {
        this.companyRepresentativeId = companyRepresentativeId;
    }

    public BigDecimal getTripCost() {
        return tripCost;
    }

    public void setTripCost(BigDecimal tripCost) {
        this.tripCost = tripCost;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getTouristCount() {
        return touristCount;
    }

    public void setTouristCount(int touristCount) {
        this.touristCount = touristCount;
    }

    public BigDecimal getPenaltySize() {
        return penaltySize;
    }

    public void setPenaltySize(BigDecimal penaltySize) {
        this.penaltySize = penaltySize;
    }
}
