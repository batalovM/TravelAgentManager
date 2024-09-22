package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Пункты маршрута в городе
public class RoutePointsInCity {
    private int routesPointsInCityId;
    private int routesPointsId;
    private int cityId;
    public RoutePointsInCity() {}

    public RoutePointsInCity(int routesPointsInCityId, int routesPointsId, int cityId) {
        this.routesPointsInCityId = routesPointsInCityId;
        this.routesPointsId = routesPointsId;
        this.cityId = cityId;
    }

    public int getRoutesPointsInCityId() {
        return routesPointsInCityId;
    }

    public void setRoutesPointsInCityId(int routesPointsInCityId) {
        this.routesPointsInCityId = routesPointsInCityId;
    }

    public int getRoutesPointsId() {
        return routesPointsId;
    }

    public void setRoutesPointsId(int routesPointsId) {
        this.routesPointsId = routesPointsId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
