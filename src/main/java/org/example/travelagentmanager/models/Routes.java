package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Маршруты
public class Routes {
    private int id;
    private int countryId;
    private String routeName;
    private String duration;
    public Routes() {}

    public Routes(int id, int countryId, String routeName, String duration) {
        this.id = id;
        this.countryId = countryId;
        this.routeName = routeName;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
