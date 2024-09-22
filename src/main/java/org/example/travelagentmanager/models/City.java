package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Город
public class City {
    private int cityId;
    private String cityName;
    public City() {}

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
