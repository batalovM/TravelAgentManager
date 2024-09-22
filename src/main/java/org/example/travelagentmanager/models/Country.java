package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
//Страна
public class Country {
    private int countryId;
    private String countryName;
    public Country() {}

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
