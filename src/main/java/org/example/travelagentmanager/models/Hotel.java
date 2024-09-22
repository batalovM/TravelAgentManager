package org.example.travelagentmanager.models;

/**
 * @author batal
 * @Date 19.09.2024
 */
public class Hotel {
    private int hotelId;
    private String hotelName;
    private int hotelClass;
    public Hotel() {}

    public Hotel(int hotelId, String hotelName, int hotelClass) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelClass = hotelClass;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelClass() {
        return hotelClass;
    }

    public void setHotelClass(int hotelClass) {
        this.hotelClass = hotelClass;
    }
}
